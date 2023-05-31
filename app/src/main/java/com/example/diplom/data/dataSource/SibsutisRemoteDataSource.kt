package com.example.diplom.data.dataSource

import com.example.diplom.data.remote.entity.ApiLesson
import com.example.diplom.data.remote.entity.ApiNews
import com.example.diplom.data.remote.entity.ApiUser
import com.example.diplom.domain.entity.ScheduleRequest
import com.example.diplom.domain.entity.UserAuthRequest
import kotlin.RuntimeException

class SibsutisRemoteDataSource(private val api: ISibsutisRemoteServices) {
    suspend fun auth(user : UserAuthRequest) : ApiUser? {
        val response = api.auth(user)
        if(response.isSuccessful){
            return response.body()
        }
        throw RuntimeException("Error while loading user from remote source")
    }

    suspend fun getSchedule(groupID: ScheduleRequest): List<ApiLesson>? {
        val response = api.getSchedule(groupID)
        if (response.isSuccessful) {
            return response.body()
        }
        throw RuntimeException("Error while loading schedule from remote source")
    }
    suspend fun getNews() : List<ApiNews>?{
        val response = api.getNews()
        if (response.isSuccessful) {
            return response.body()
        }
        throw RuntimeException("Error while loading news from remote source")
    }
}