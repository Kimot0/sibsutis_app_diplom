package com.example.diplom.data.dataSource

import com.example.diplom.data.remote.entity.*
import com.example.diplom.data.remote.network.INetwork
import com.example.diplom.domain.entity.*
import retrofit2.Call
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

    @POST("teacher")
    suspend fun getDisciplines(@Body group: ScheduleRequest): Response<List<ApiDiscipline>>

    @POST("sendlist")
    suspend fun sendGroupList(@Body group: ApiHeadSendList):Response<String>

    @POST("getlist")
    suspend fun getGroupListForTeacher(@Body teacherFIO: TeacherGetGroupListRequest):Response<List<ApiGetHeadList>>

    @POST("sendteacherlist")
    suspend fun sendGroupListByTeacher(@Body list: HeadListForTeacher) : Response<String>
}
