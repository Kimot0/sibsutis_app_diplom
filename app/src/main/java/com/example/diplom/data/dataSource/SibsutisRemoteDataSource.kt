package com.example.diplom.data.dataSource

import com.example.diplom.data.remote.network.safeApiCall
import com.example.diplom.domain.entity.ScheduleRequest
import com.example.diplom.domain.entity.UserAuthRequest

class SibsutisRemoteDataSource(private val api: ISibsutisRemoteServices) {
    suspend fun auth(user : UserAuthRequest) = safeApiCall { api.auth(user) }
    suspend fun getSchedule(groupID: ScheduleRequest) = safeApiCall { api.getSchedule(groupID) }
    suspend fun getNews() = safeApiCall { api.getNews() }
}