package com.example.diplom.domain.entity

data class UserAuthResult(
    val group: String,
    val role: Role,
    val fio: String,
)
