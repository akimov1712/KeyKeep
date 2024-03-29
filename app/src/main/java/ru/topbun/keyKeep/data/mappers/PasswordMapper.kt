package ru.topbun.keyKeep.data.mappers

import ru.topbun.keyKeep.data.EncryptionHelper
import ru.topbun.keyKeep.data.database.entities.PasswordDBO
import ru.topbun.keyKeep.domain.enities.PasswordEntity
import javax.inject.Inject

object PasswordMapper{

    fun mapEntityToDBO(entity: PasswordEntity) = PasswordDBO(
        id = entity.id,
        name = entity.name,
        site = entity.site,
        email = entity.email,
        password = EncryptionHelper.encrypt(entity.password),
    )

    fun mapDBOToEntity(entity: PasswordDBO) = PasswordEntity(
        id = entity.id,
        name = entity.name,
        site = entity.site,
        email = entity.email,
        password = EncryptionHelper.decrypt(entity.password),
    )

}