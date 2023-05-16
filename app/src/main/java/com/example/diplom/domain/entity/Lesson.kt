package com.example.diplom.domain.entity

data class Lesson(
    val lessonTime: String,
    val teacher: String,
    val classroom: String,
    val typeOfLesson: String,
    val name: String,
    val weekType: String,
    val groupID: String?,
    val weekDay:String,
)