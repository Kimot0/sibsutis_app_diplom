package com.example.diplom.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(
    tableName = "disciplines"
)

data class DisciplineDbEntity(
    @PrimaryKey
    val id: Int,
    val uuid: String,
    val academicSemestr: Int,
    val academicYear: String,
    val course: Int,
    val discipline: String,
    val group: String,
    val lessonType: String,
    val teacherFIO: String
)
