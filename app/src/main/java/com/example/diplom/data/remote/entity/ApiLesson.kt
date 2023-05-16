package com.example.diplom.data.remote.entity

import com.example.diplom.domain.entity.Lesson
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class ApiLesson(
    @Json(name = "dayOfWeek")
    val dayOfWeek: String,
    @Json(name = "groupID")
    val groupID: String,
    @Json(name = "subjectName")
    val subjectName: String,
    @Json(name = "subjectType")
    val subjectType: String,
    @Json(name = "teacherName")
    val teacherName: String,
    @Json(name = "startTime")
    val startTime: String,
    @Json(name = "studyRoom")
    val studyRoom: String,
    @Json(name = "weekType")
    val weekType: String
)

fun ApiLesson.toLesson() = Lesson(
    lessonTime = this.startTime,
    teacher = this.teacherName,
    classroom = this.studyRoom,
    typeOfLesson = this.subjectType,
    name = this.subjectName,
    weekType = this.weekType,
    groupID = this.groupID,
    weekDay = this.dayOfWeek
)
