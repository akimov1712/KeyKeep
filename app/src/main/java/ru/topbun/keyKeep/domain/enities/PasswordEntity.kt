package ru.topbun.keyKeep.domain.enities

data class PasswordEntity(
    val name: String,
    val site: String,
    val email: String?,
    val password: String,
    val id: Int = 0,
)
