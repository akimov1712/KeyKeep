package ru.topbun.keyKeep.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.topbun.keyKeep.data.repositories.PasswordRepositoryImpl
import ru.topbun.keyKeep.data.repositories.SecurityRepositoryImpl
import ru.topbun.keyKeep.domain.repositories.PasswordRepository
import ru.topbun.keyKeep.domain.repositories.SecurityRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindPasswordRepository(repository: PasswordRepositoryImpl): PasswordRepository


    @Binds
    @Singleton
    fun bindSecurityRepository(repository: SecurityRepositoryImpl): SecurityRepository

    companion object{

        private const val SECURITY_PREFS_NAME = "security_prefs_name"

        @Provides
        @Singleton
        fun provideSharedPrefs(application: Application): SharedPreferences{
            return application.getSharedPreferences(SECURITY_PREFS_NAME, Context.MODE_PRIVATE)
        }

    }

}