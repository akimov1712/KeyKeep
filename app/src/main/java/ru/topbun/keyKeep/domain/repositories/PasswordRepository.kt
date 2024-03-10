package ru.topbun.keyKeep.domain.repositories

import kotlinx.coroutines.flow.Flow
import ru.topbun.keyKeep.domain.enities.PasswordEntity

interface PasswordRepository {

    suspend fun getPasswordList(): Flow<List<PasswordEntity>>
    suspend fun getPasswordWithId(id: Int): PasswordEntity
    suspend fun getPasswordWithSearchRequest(query: String): Flow<List<PasswordEntity>>
    suspend fun addPassword(password: PasswordEntity)
    suspend fun deletePassword(id: Int)

}