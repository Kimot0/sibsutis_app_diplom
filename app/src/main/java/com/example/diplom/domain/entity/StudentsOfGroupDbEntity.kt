package com.example.diplom.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.diplom.data.remote.entity.ApiStudentOfGroup

@Entity(
    tableName = "groups"
)

data class StudentsOfGroupDbEntity(
    @PrimaryKey
    val id: String,
    val studentGroup: String,
    val fio: String
)

fun StudentsOfGroupDbEntity?.toStudentOfGroup() = StudentOfGroup(
    id = this?.id ?: "0",
    studentGroup = this?.studentGroup ?: "None",
    fio = this?.fio ?: "Unknown user"
)
