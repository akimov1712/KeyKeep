package ru.topbun.keyKeep.domain.repositories

import kotlinx.coroutines.flow.Flow
import ru.topbun.keyKeep.domain.enities.FingerResponseEntity

interface SecurityRepository {

    suspend fun checkSetMasterPassword(): Boolean
    suspend fun checkCurrentMasterPassword(password: String): Boolean
    suspend fun setMasterPassword(password: String)
    fun checkFingerPassword(): Flow<FingerResponseEntity>

}