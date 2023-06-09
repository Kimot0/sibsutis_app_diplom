package com.example.diplom.data.remote.entity

import com.example.diplom.domain.entity.StudentsOfGroupDbEntity
import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.util.UUID

@JsonClass(generateAdapter = true)
data class ApiStudentOfGroup(
    @Json(name = "fio")
    val fio: String
)

fun ApiStudentOfGroup?.toStudentOfGroupDbEntity(group: String) = StudentsOfGroupDbEntity(
    id = UUID.randomUUID().toString(),
    studentGroup = group,
    fio = this?.fio ?: "Unknown student"
)
