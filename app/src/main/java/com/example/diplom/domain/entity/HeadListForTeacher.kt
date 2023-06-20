package com.example.diplom.domain.entity

import com.example.diplom.data.remote.entity.ApiHeadSendList

data class HeadListForTeacher(
    val id: Int,
    val group: String,
    val discipline: String,
    val lessonType: String,
    val teacherFIO: String,
    val date: String,
    val groupList: String,
    val confirm: Boolean
)

fun HeadListForTeacher.toTeacherAttendance(): TeacherAttendance = TeacherAttendance(
    id = this.id,
    groupID = this.group,
    discipline = this.discipline,
    lessonType = this.lessonType,
    teacherFIO = this.teacherFIO,
    date = this.date
)
