package ru.topbun.keyKeep.di

import android.app.Application
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import ru.topbun.keyKeep.data.source.locale.database.AppDatabase
import ru.topbun.keyKeep.data.source.locale.database.dao.PasswordDao
import javax.inject.Singleton

interface RoomModule {

    companion object{

        @Provides
        @Singleton
        fun providePasswordDao(@ApplicationContext application: Application): PasswordDao{
            return AppDatabase.getInstance(application).passwordDao()
        }

    }

}