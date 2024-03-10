package ru.topbun.keyKeep.domain.useCases.security

import ru.topbun.keyKeep.domain.repositories.SecurityRepository

class CheckSetMasterPasswordUseCase(private val repository: SecurityRepository) {

    suspend operator fun invoke() = repository.checkSetMasterPassword()

}