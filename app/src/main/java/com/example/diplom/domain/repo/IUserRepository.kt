package com.example.diplom.domain.repo

import com.example.diplom.domain.entity.UserAuthRequest
import com.example.diplom.domain.entity.UserAuthResult

interface IUserRepository {
    suspend fun auth(user : UserAuthRequest):UserAuthResult
}