package ru.topbun.keyKeep.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.topbun.keyKeep.domain.enities.PasswordEntity

@Entity(tableName = "password")
data class PasswordDBO(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val site: String,
    val email: String?,
    val password: String
)