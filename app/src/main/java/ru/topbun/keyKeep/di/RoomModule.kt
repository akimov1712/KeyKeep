package ru.topbun.keyKeep.di

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.topbun.keyKeep.data.database.AppDatabase
import ru.topbun.keyKeep.data.database.dao.PasswordDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RoomModule {

    companion object{

        @Provides
        @Singleton
        fun providePasswordDao(application: Application): PasswordDao {
            return AppDatabase.getInstance(application).passwordDao()
        }

    }

}