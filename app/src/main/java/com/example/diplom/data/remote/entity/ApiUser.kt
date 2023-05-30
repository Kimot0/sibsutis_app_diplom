package com.example.diplom.data.remote.entity

import com.example.diplom.domain.entity.Role
import com.example.diplom.domain.entity.UserAuthResult
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiUser(
    @Json(name = "group")
    val group: String,
    @Json(name = "role")
    val role: Role,
    @Json(name = "fio")
    val fio: String
)
fun ApiUser?.toUserAuthResult() = UserAuthResult(
    group = this?.group ?: "",
    role = this?.role ?: Role.STUDENT,
    fio = this?.fio ?: "Unknown user",
)

