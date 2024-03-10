package ru.topbun.keyKeep.data.repositories

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.map
import ru.topbun.keyKeep.data.database.dao.PasswordDao
import ru.topbun.keyKeep.data.mappers.PasswordMapper
import ru.topbun.keyKeep.domain.enities.PasswordEntity
import ru.topbun.keyKeep.domain.repositories.PasswordRepository
import javax.inject.Inject

class PasswordRepositoryImpl @Inject constructor(
    private val passwordDao: PasswordDao,
    private val mapper: PasswordMapper
): PasswordRepository {

    override suspend fun getPasswordWithSearchRequest(query: String): Flow<List<PasswordEntity>> {
        return passwordDao.getWithSearchRequest(query).map { passwordList ->
            passwordList.map { passwordItem ->
                mapper.mapDBOToEntity(passwordItem)
            }
        }
    }

    override suspend fun getPasswordList(): Flow<List<PasswordEntity>> {
        return passwordDao.getList().map { passwordList ->
            passwordList.map { passwordItem ->
                mapper.mapDBOToEntity(passwordItem)
            }
        }
    }

    override suspend fun getPasswordWithId(id: Int): Flow<PasswordEntity> {
        return passwordDao.getWithId(id).map { mapper.mapDBOToEntity(it) }

    }

    override suspend fun addPassword(password: PasswordEntity){
        passwordDao.insert(mapper.mapEntityToDBO(password))
    }

    override suspend fun deletePassword(id: Int){
        passwordDao.delete(id)
    }

}