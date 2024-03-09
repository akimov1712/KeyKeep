package ru.topbun.keyKeep.data.mappers

import ru.topbun.keyKeep.data.database.entities.PasswordDBO
import ru.topbun.keyKeep.domain.enities.PasswordEntity
import javax.inject.Inject

class PasswordMapper @Inject constructor(){

    fun mapEntityToDBO(entity: PasswordEntity) = PasswordDBO(
        id = entity.id,
        name = entity.name,
        site = entity.site,
        email = entity.email,
        password = entity.password,
    )

    fun mapDBOToEntity(entity: PasswordDBO) = PasswordEntity(
        id = entity.id,
        name = entity.name,
        site = entity.site,
        email = entity.email,
        password = entity.password,
    )

}