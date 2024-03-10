package ru.topbun.keyKeep.domain.enities

data class FingerResponseEntity(
    val state: FingerStateEnum,
    val message: String
)
