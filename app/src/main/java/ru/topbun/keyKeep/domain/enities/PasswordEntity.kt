package ru.topbun.keyKeep.domain.enities

data class PasswordEntity(
    val id: Int,
    val name: String,
    val site: String,
    val email: String?,
    val password: String
)
