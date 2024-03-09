package ru.topbun.keyKeep.data.source.locale.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.topbun.keyKeep.data.source.locale.database.entities.PasswordDBO

@Dao
interface PasswordDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(password: PasswordDBO)

    @Query("DELETE FROM password WHERE id=:id")
    suspend fun delete(id: Int)

    @Query("SELECT * FROM password")
    fun getList(): Flow<List<PasswordDBO>>

    @Query("SELECT * FROM password WHERE id=:id LIMIT 1")
    fun getWithId(id: Int): Flow<PasswordDBO>

}