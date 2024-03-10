package ru.topbun.keyKeep.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.topbun.keyKeep.domain.repositories.PasswordRepository
import ru.topbun.keyKeep.domain.repositories.SecurityRepository
import ru.topbun.keyKeep.domain.useCases.password.AddPasswordUseCase
import ru.topbun.keyKeep.domain.useCases.password.DeletePasswordUseCase
import ru.topbun.keyKeep.domain.useCases.password.GetPasswordListUseCase
import ru.topbun.keyKeep.domain.useCases.password.GetPasswordWithIdUseCase
import ru.topbun.keyKeep.domain.useCases.password.GetPasswordWithSearchRequestUseCase
import ru.topbun.keyKeep.domain.useCases.security.CheckFingerScanUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface UseCasesModule {

    companion object{

        @Provides
        @Singleton
        fun provideAddPasswordUseCase(repository: PasswordRepository): AddPasswordUseCase {
            return AddPasswordUseCase(repository)
        }

        @Provides
        @Singleton
        fun provideGetPasswordListUseCase(repository: PasswordRepository): GetPasswordListUseCase {
            return GetPasswordListUseCase(repository)
        }

        @Provides
        @Singleton
        fun provideGetPasswordUseCase(repository: PasswordRepository): GetPasswordWithIdUseCase {
            return GetPasswordWithIdUseCase(repository)
        }

        @Provides
        @Singleton
        fun provideDeletePasswordUseCase(repository: PasswordRepository): DeletePasswordUseCase {
            return DeletePasswordUseCase(repository)
        }

        @Provides
        @Singleton
        fun provideGetPasswordWithSearchRequestUseCase(repository: PasswordRepository): GetPasswordWithSearchRequestUseCase {
            return GetPasswordWithSearchRequestUseCase(repository)
        }

        @Provides
        @Singleton
        fun provideCheckFingerScanUseCase(repository: SecurityRepository): CheckFingerScanUseCase {
            return CheckFingerScanUseCase(repository)
        }

    }

}