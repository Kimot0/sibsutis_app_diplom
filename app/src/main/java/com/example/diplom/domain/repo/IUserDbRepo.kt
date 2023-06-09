package com.example.diplom.domain.repo

import com.example.diplom.domain.entity.UserAuthResult

interface IUserDbRepo {
    suspend fun saveUser(user: UserAuthResult)
    suspend fun clearUser()
}