package ru.topbun.keyKeep.domain.useCases

import ru.topbun.keyKeep.domain.repositories.PasswordRepository

class GetPasswordListUseCase(private val repository: PasswordRepository) {

    suspend operator fun invoke() = repository.getPasswordList()

}