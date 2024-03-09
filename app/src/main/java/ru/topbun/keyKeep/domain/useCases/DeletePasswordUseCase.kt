package ru.topbun.keyKeep.domain.useCases

import ru.topbun.keyKeep.domain.repositories.PasswordRepository

class DeletePasswordUseCase(private val repository: PasswordRepository) {

    suspend operator fun invoke(id: Int) = repository.deletePassword(id)

}