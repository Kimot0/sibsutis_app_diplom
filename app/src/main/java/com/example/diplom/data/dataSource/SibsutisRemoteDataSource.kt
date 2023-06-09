package com.example.diplom.data.dataSource

import com.example.diplom.data.remote.entity.ApiStudentOfGroup
import com.example.diplom.data.remote.entity.ApiUser
import com.example.diplom.data.remote.network.safeApiCall
import com.example.diplom.domain.entity.ScheduleRequest
import com.example.diplom.domain.entity.UserAuthRequest
import com.example.diplom.domain.entity.UsersRequest
import java.lang.RuntimeException

class SibsutisRemoteDataSource(private val api: ISibsutisRemoteServices) {

    suspend fun auth(user: UserAuthRequest): ApiUser {
        val response =  api.auth(user)
        if (response.isSuccessful) {
            response.body()?.let { user ->
                return user
            }
        }
        throw RuntimeException("Error while auth")
    }

    suspend fun getSchedule(groupID: ScheduleRequest) = safeApiCall { api.getSchedule(groupID) }
    suspend fun getNews() = safeApiCall { api.getNews() }

    suspend fun getGroup(group: String): List<ApiStudentOfGroup> {
        val response = api.getGroup(UsersRequest(group))
        if (response.isSuccessful) {
            response.body()?.let { group ->
                return group
            }
        }
        throw RuntimeException("Error while getting group")
    }
}