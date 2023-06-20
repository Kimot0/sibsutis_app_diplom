package com.example.diplom.data.dataSource

import com.example.diplom.data.remote.entity.*
import com.example.diplom.data.remote.network.safeApiCall
import com.example.diplom.domain.entity.*
import retrofit2.Response
import retrofit2.http.Body
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

    suspend fun getDisciplines(group: String): List<ApiDiscipline> {
        val response = api.getDisciplines(ScheduleRequest(group))
        if (response.isSuccessful) {
            response.body()?.let { disciplines ->
                return disciplines
            }
        }
        throw RuntimeException("Error while getting disciplines")
    }

    suspend fun sendGroupList(@Body group: ApiHeadSendList): String {
        val response = api.sendGroupList(group)
        if (response.isSuccessful) {
            response.body()?.let { it ->
                return it
            }
        }
        throw RuntimeException("Error while getting disciplines")
    }

    suspend fun getGroupListForTeacher(teacherFIO: TeacherGetGroupListRequest): List<ApiGetHeadList> {
        val response = api.getGroupListForTeacher(teacherFIO)
        if (response.isSuccessful) {
            response.body()?.let { disciplines ->
                return disciplines
            }
        }
        throw RuntimeException("Error while getting disciplines")
    }

    suspend fun sendGroupListByTeacher(list: HeadListForTeacher): String {
        val response = api.sendGroupListByTeacher(list)
        if (response.isSuccessful) {
            response.body()?.let { disciplines ->
                return disciplines
            }
        }
        throw RuntimeException("Error while getting disciplines")
    }
}