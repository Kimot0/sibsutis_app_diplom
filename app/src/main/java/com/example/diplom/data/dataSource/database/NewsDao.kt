package com.example.diplom.data.dataSource.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.diplom.data.remote.entity.ApiDbNews


@Dao
interface NewsDao {
    @Query("Select * from news")
    suspend fun getAllNews():List<ApiDbNews>

    @Upsert
    suspend fun insertNews(news: ApiDbNews)

    @Query("Delete from news")
    suspend fun deleteOldNews()
}