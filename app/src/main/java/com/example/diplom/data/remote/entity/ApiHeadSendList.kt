package com.example.diplom.data.remote.entity

data class ApiHeadSendList(
    val id: Int,
    val group: String,
    val discipline: String,
    val lessonType: String,
    val teacherFIO: String,
    val date: String,
    val groupList: Map<String,String?>,
    val confirm: Boolean
)