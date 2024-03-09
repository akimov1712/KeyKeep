package ru.topbun.keyKeep.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.topbun.keyKeep.domain.repositories.PasswordRepository
import ru.topbun.keyKeep.domain.useCases.AddPasswordUseCase
import ru.topbun.keyKeep.domain.useCases.DeletePasswordUseCase
import ru.topbun.keyKeep.domain.useCases.GetPasswordListUseCase
import ru.topbun.keyKeep.domain.useCases.GetPasswordUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface UseCasesModule {

    companion object{

        @Provides
        @Singleton
        fun bindAddPasswordUseCase(repository: PasswordRepository): AddPasswordUseCase{
            return AddPasswordUseCase(repository)
        }

        @Provides
        @Singleton
        fun bindGetPasswordListUseCase(repository: PasswordRepository): GetPasswordListUseCase{
            return GetPasswordListUseCase(repository)
        }

        @Provides
        @Singleton
        fun bindGetPasswordUseCase(repository: PasswordRepository): GetPasswordUseCase{
            return GetPasswordUseCase(repository)
        }

        @Provides
        @Singleton
        fun bindDeletePasswordUseCase(repository: PasswordRepository): DeletePasswordUseCase{
            return DeletePasswordUseCase(repository)
        }

    }

}