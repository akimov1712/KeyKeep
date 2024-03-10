package ru.topbun.keyKeep.domain.useCases.security

import ru.topbun.keyKeep.domain.repositories.SecurityRepository

class CheckCurrentMasterPasswordUseCase(private val repository: SecurityRepository) {

    suspend operator fun invoke(password: String) = repository.checkCurrentMasterPassword(password)

}