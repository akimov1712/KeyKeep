package ru.topbun.keyKeep.domain.useCases

import ru.topbun.keyKeep.domain.repositories.PasswordRepository

class GetPasswordWithSearchRequestUseCase(private val repository: PasswordRepository) {

    suspend operator fun invoke(query: String) = repository.getPasswordWithSearchRequest(query)

}