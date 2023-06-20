package com.example.diplom.domain.entity

import com.example.diplom.data.remote.entity.ApiGetHeadList

data class TeacherAttendance(
    val id: Int,
    val groupID: String?,
    val discipline: String?,
    val lessonType: String?,
    val teacherFIO: String?,
    val date: String?
)

fun TeacherAttendance.toHeadListForTeacher() = HeadListForTeacher(
    id = this.id,
    group = this.groupID?:"",
    discipline = this.discipline ?:"",
    lessonType = this.lessonType?:"",
    teacherFIO = this.teacherFIO?:"",
    date = this.date?:"",
    groupList = "" ,
    confirm = false
)