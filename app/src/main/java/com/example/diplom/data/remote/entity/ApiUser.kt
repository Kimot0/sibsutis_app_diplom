package com.example.diplom.data.remote.entity

import com.example.diplom.domain.entity.UserAuthResult
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiUser(
    @Json(name = "group")
    val group: String,
    @Json(name = "role")
    val role: String
)
fun ApiUser?.toUserAuthResult() = UserAuthResult(
    group = this?.group ?: "",
    role = this?.role ?:""
)
