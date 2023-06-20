package com.example.diplom.data.remote.entity

import com.example.diplom.domain.entity.DisciplineDbEntity
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiDiscipline(
    @Json(name = "id")
    val id: Int,
    @Json(name = "UUID")
    val uuid: String,
    @Json(name = "academicSemestr")
    val academicSemestr: Int,
    @Json(name = "academicYear")
    val academicYear: String,
    @Json(name = "course")
    val course: Int,
    @Json(name = "discipline")
    val discipline: String,
    @Json(name = "group")
    val group: String,
    @Json(name = "lessonType")
    val lessonType: String,
    @Json(name = "teacherFIO")
    val teacherFIO: String
)

fun ApiDiscipline.toDisciplinesDbEntity() = DisciplineDbEntity(
    id = this.id,
    uuid = this.uuid,
    academicSemestr = this.academicSemestr,
    academicYear = this.academicYear,
    course = this.course,
    discipline = this.discipline,
    group = this.group,
    lessonType = this.lessonType,
    teacherFIO = this.teacherFIO
)
