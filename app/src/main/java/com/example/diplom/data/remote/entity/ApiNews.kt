package com.example.diplom.data.remote.entity

import com.example.diplom.domain.entity.News
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiNews(
    @Json(name = "title")
    val title: String,
    @Json(name = "content")
    val content: String,
    @Json(name = "author")
    val author: String,
    @Json(name = "dateTime")
    val dateTime:String
)

fun ApiNews.toNews() = News(
    title = this.title,
    content = this.content,
    author = this.author,
    dateTime = this.dateTime
)