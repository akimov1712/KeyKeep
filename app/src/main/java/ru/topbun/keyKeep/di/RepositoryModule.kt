package ru.topbun.keyKeep.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.topbun.keyKeep.data.repositories.PasswordRepositoryImpl
import ru.topbun.keyKeep.domain.repositories.PasswordRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindPasswordRepository(repository: PasswordRepositoryImpl): PasswordRepository

}