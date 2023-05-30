package com.example.diplom.data.remote.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.diplom.domain.entity.News

@Entity(
    tableName = "news"
)
data class ApiDbNews(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    @ColumnInfo(name = "title")
    val title: String?,
    @ColumnInfo(name = "content")
    val content: String?,
    @ColumnInfo(name = "datetime")
    val dateTime: String?,
    @ColumnInfo(name = "author")
    val author: String?
)
fun ApiDbNews.toNews() = News(
    title = this.title?:"",
    content = this.content?:"",
    dateTime = this.dateTime?:"",
    author = this.author?:"",
    id = this.id
)
