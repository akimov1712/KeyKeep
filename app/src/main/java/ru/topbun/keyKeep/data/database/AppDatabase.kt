package ru.topbun.keyKeep.data.database

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.topbun.keyKeep.data.database.dao.PasswordDao
import ru.topbun.keyKeep.data.database.entities.PasswordDBO

@Database(
    entities = [PasswordDBO::class],
    version = 1,
    exportSchema = true
)
abstract class AppDatabase: RoomDatabase() {

    abstract fun passwordDao(): PasswordDao

    companion object{

        private var INSTANCE: AppDatabase? = null
        private const val DB_NAME = "keyKeep.db"

        fun getInstance(application: Application) = INSTANCE ?: synchronized(this){
            INSTANCE ?: buildDatabase(application).also { INSTANCE = it }
        }

        private fun buildDatabase(application: Application) = Room.databaseBuilder(
            application,
            AppDatabase::class.java,
            DB_NAME
        ).build()

    }

}