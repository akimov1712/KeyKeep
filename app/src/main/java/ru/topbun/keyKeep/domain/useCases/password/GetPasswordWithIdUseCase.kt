package ru.topbun.keyKeep.domain.useCases.password

import ru.topbun.keyKeep.domain.repositories.PasswordRepository

class GetPasswordWithIdUseCase(private val repository: PasswordRepository) {

    suspend operator fun invoke(id: Int) = repository.getPasswordWithId(id)

}