package ru.topbun.keyKeep.data.source.locale.database.entities

import androidx.room.Entity

@Entity(tableName = "password")
data class PasswordDBO(
    val id: Int,
    val name: String,
    val site: String,
    val email: String?,
    val password: String
)