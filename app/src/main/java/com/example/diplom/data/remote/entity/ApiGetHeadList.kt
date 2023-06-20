package com.example.diplom.data.remote.entity

import com.example.diplom.domain.entity.HeadListForTeacher
import com.example.diplom.domain.entity.TeacherAttendance
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiGetHeadList(
    @Json(name = "id")
    val id: Int,
    @Json(name = "group")
    val group: String,
    @Json(name = "discipline")
    val discipline: String,
    @Json(name = "lessonType")
    val lessonType: String,
    @Json(name = "teacherFIO")
    val teacherFIO: String,
    @Json(name = "date")
    val date: String,
    @Json(name = "groupList")
    val groupList: String,
    @Json(name = "confirm")
    val confirm: Boolean
)

fun ApiGetHeadList.toGetHeadList() = HeadListForTeacher(
    id = this.id,
    group = this.group,
    discipline = this.discipline ,
    lessonType = this.lessonType,
    teacherFIO = this.teacherFIO,
    date = this.date,
    groupList = this.groupList,
    confirm = this.confirm
)

fun ApiGetHeadList.toTeacherAttendance() = TeacherAttendance(
    id = this.id,
    groupID = this.group,
    discipline = this.discipline ,
    lessonType = this.lessonType,
    teacherFIO = this.teacherFIO,
    date = this.date,
)

fun ApiGetHeadList.toApiDB() = ApiDbListForTeacher(
    id = this.id,
    group = this.group,
    discipline = this.discipline ,
    lessonType = this.lessonType,
    teacherFIO = this.teacherFIO,
    date = this.date,
    groupList = this.groupList
)