package ru.topbun.keyKeep.domain.useCases.security

import ru.topbun.keyKeep.domain.repositories.SecurityRepository

class CheckFingerScanUseCase(private val securityRepository: SecurityRepository) {

    operator fun invoke() = securityRepository.checkFingerPassword()

}