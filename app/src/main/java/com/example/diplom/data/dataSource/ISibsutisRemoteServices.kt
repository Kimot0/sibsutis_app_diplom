package com.example.diplom.data.dataSource

import com.example.diplom.data.remote.entity.ApiLesson
import com.example.diplom.data.remote.entity.ApiNews
import com.example.diplom.data.remote.entity.ApiUser
import com.example.diplom.data.remote.network.INetwork
import com.example.diplom.domain.entity.ScheduleRequest
import com.example.diplom.domain.entity.UserAuthRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

fun provideSibsutisServices(network: INetwork): ISibsutisRemoteServices =
    network.retrofit.create(
        ISibsutisRemoteServices::class.java
    )

interface ISibsutisRemoteServices{
    @POST("login")
    suspend fun auth(@Body userAuth: UserAuthRequest): ApiUser

    @POST("schedule")
    suspend fun getSchedule(@Body groupID: ScheduleRequest): Response<List<ApiLesson>>

    @POST("news")
    suspend fun getNews():Response<List<ApiNews>>
}
