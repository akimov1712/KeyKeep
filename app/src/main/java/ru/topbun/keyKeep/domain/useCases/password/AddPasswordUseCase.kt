package ru.topbun.keyKeep.domain.useCases.password

import ru.topbun.keyKeep.domain.enities.PasswordEntity
import ru.topbun.keyKeep.domain.repositories.PasswordRepository

class AddPasswordUseCase(private val repository: PasswordRepository) {

    suspend operator fun invoke(password: PasswordEntity) = repository.addPassword(password)

}