package com.example.diplom.data.dataSource.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.diplom.data.remote.entity.ApiDbAccount
import com.example.diplom.data.remote.entity.ApiDbNews
import com.example.diplom.data.remote.entity.ApiDbSchedule
import com.example.diplom.data.remote.entity.ApiDbScheduleGroup

@Database(
    version = 1,
    entities = [
        ApiDbAccount::class,
        ApiDbNews::class,
        ApiDbSchedule::class,
        ApiDbScheduleGroup::class
    ]
)
abstract class AppDatabase:RoomDatabase() {
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

    abstract fun getAccountsDao():AccountsDao
    abstract fun getNewsDao():NewsDao
    abstract fun getScheduleDao():ScheduleDao
    abstract fun getScheduleGroupsDao(): ScheduleGroupsDao
}