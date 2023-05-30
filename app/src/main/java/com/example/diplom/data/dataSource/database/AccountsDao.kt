package com.example.diplom.data.dataSource.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.diplom.data.remote.entity.AccountSignInTuple
import com.example.diplom.data.remote.entity.ApiDbAccount

@Dao
interface AccountsDao {
    @Query("Select `group` From accounts where username = :name")
    suspend fun findGroup(name:String?): AccountSignInTuple

    @Insert
    suspend fun addUser(account: ApiDbAccount)
}