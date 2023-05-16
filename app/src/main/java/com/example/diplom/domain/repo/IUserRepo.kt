package com.example.diplom.domain.repo

import com.example.diplom.domain.Requests
import com.example.diplom.domain.entity.UserAuthRequest
import com.example.diplom.domain.entity.UserAuthResult

interface IUserRepo {
    suspend fun auth(user : UserAuthRequest):Requests<UserAuthResult>
}