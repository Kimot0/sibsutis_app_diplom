package com.example.diplom.data.dataSource.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.diplom.data.remote.entity.ApiDbAccount

@Database(
    version = 1,
    entities = [
        ApiDbAccount::class
    ]
)
abstract class AppDatabase:RoomDatabase() {

    abstract fun getAccountsDao():AccountsDao

}