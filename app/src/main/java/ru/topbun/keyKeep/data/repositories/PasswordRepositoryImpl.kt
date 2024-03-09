package ru.topbun.keyKeep.data.repositories

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.topbun.keyKeep.data.database.dao.PasswordDao
import ru.topbun.keyKeep.data.database.entities.PasswordDBO
import ru.topbun.keyKeep.domain.enities.PasswordEntity
import ru.topbun.keyKeep.domain.repositories.PasswordRepository
import javax.inject.Inject

class PasswordRepositoryImpl @Inject constructor(
    private val passwordDao: PasswordDao
): PasswordRepository {

    override suspend fun getPasswordList(): Flow<List<PasswordEntity>> {
        return passwordDao.getList().map { passwordList ->
            passwordList.map { passwordItem ->
                passwordItem.mapToEntity()
            }
        }
    }

    override suspend fun getPassword(id: Int): Flow<PasswordEntity> {
        return passwordDao.getWithId(id).map { it.mapToEntity() }
    }

    override suspend fun addPassword(password: PasswordEntity){
        passwordDao.insert(PasswordDBO.mapToDBO(password))
    }

    override suspend fun deletePassword(id: Int){
        passwordDao.delete(id)
    }

}