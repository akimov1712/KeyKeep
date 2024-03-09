package ru.topbun.keyKeep.domain.repositories

import kotlinx.coroutines.flow.Flow
import ru.topbun.keyKeep.domain.enities.PasswordEntity

interface PasswordRepository {

    suspend fun getPasswordList(): Flow<List<PasswordEntity>>
    suspend fun getPassword(id: Int): Flow<PasswordEntity>
    suspend fun addPassword(password: PasswordEntity)
    suspend fun deletePassword(id: Int)

}