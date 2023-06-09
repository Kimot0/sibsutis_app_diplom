package com.example.diplom.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (
    tableName = "user"
)

data class UserDbEntity(
    @PrimaryKey
    val id: String,
    val group: String?,
    val role: Role,
    val fio: String
)

fun UserDbEntity?.toUserAuthResult() = UserAuthResult(
    group = this?.group,
    role = this?.role ?: Role.STUDENT,
    fio = this?.fio ?: "Unknown user",
)

