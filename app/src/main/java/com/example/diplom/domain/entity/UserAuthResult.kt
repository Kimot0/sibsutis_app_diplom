package com.example.diplom.domain.entity

import java.util.UUID

data class UserAuthResult(
    val group: String?,
    val role: Role,
    val fio: String,
)

fun UserAuthResult?.toUserDbEntity() = UserDbEntity(
    id = UUID.randomUUID().toString(),
    group = this?.group,
    role = this?.role ?: Role.STUDENT,
    fio = this?.fio ?: "Unknown user",
)
