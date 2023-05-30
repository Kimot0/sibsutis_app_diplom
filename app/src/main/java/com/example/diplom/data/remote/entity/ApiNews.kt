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
    val dateTime:String,
    @Json(name = "id")
    val id:Int?
)

fun ApiNews.toNews() = News(
    title = this.title,
    content = this.content,
    author = this.author,
    dateTime = this.dateTime,
    id = this.id?:-1
)

fun ApiNews.toApiDbNews() = ApiDbNews(
    id = this.id?:-1,
    title = this.title,
    content = this.content,
    dateTime = this.dateTime,
    author = this.author
)