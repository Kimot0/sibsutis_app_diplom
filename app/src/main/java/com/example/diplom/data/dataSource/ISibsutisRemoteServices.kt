package com.example.diplom.data.dataSource

import com.example.diplom.data.remote.entity.ApiLesson
import com.example.diplom.data.remote.entity.ApiNews
import com.example.diplom.data.remote.entity.ApiStudentOfGroup
import com.example.diplom.data.remote.entity.ApiUser
import com.example.diplom.data.remote.network.INetwork
import com.example.diplom.domain.entity.ScheduleRequest
import com.example.diplom.domain.entity.UserAuthRequest
import com.example.diplom.domain.entity.UsersRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

fun provideSibsutisServices(network: INetwork): ISibsutisRemoteServices =
    network.retrofit.create(
        ISibsutisRemoteServices::class.java
    )

interface ISibsutisRemoteServices{
    @POST("login")
    suspend fun auth(@Body userAuth: UserAuthRequest): Response<ApiUser>

    @POST("schedule")
    suspend fun getSchedule(@Body groupID: ScheduleRequest): List<ApiLesson>

    @POST("news")
    suspend fun getNews():List<ApiNews>

    @POST("users")
    suspend fun getGroup(@Body group: UsersRequest): Response<List<ApiStudentOfGroup>>
}
