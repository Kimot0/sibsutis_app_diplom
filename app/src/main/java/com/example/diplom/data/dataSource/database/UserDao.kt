package com.example.diplom.data.dataSource.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.diplom.domain.entity.UserDbEntity

@Dao
interface UserDao {

    @Query("DELETE FROM user")
    suspend fun clearUserTable()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(userDbEntity: UserDbEntity)
}