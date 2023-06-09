package com.example.diplom.data.dataSource.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.diplom.data.remote.entity.ApiDbNews
import com.example.diplom.domain.entity.StudentsOfGroupDbEntity
import com.example.diplom.domain.entity.UserDbEntity

@Database(
    version = 1,
    entities = [
        UserDbEntity::class,
        ApiDbNews::class,
        StudentsOfGroupDbEntity::class
    ]
)

abstract class AppDatabase : RoomDatabase() {
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

    abstract fun getUserDao(): UserDao
    abstract fun getNewsDao(): NewsDao
    abstract fun getGroupDao(): GroupDao
}