package com.example.diplom.domain.repo

import com.example.diplom.data.remote.entity.ApiDiscipline
import com.example.diplom.data.remote.entity.ApiStudentOfGroup
import com.example.diplom.domain.entity.UserAuthRequest
import com.example.diplom.domain.entity.UserAuthResult

interface IUserRepo {
    suspend fun auth(user: UserAuthRequest): UserAuthResult
    suspend fun clearLoggedUser()
    suspend fun saveLoggedUser(user: UserAuthResult)
    suspend fun getGroup(group: String): List<ApiStudentOfGroup>
}