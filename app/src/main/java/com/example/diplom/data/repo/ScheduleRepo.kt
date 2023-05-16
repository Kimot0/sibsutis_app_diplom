package com.example.diplom.data.repo

import com.example.diplom.data.dataSource.SibsutisRemoteDataSource
import com.example.diplom.data.remote.entity.ApiLesson
import com.example.diplom.data.remote.entity.toLesson
import com.example.diplom.data.remote.entity.toUserAuthResult
import com.example.diplom.domain.Requests
import com.example.diplom.domain.entity.Lesson
import com.example.diplom.domain.entity.ScheduleRequest
import com.example.diplom.domain.repo.IScheduleRepo

class ScheduleRepo(private val source: SibsutisRemoteDataSource):IScheduleRepo {
    override suspend fun getSchedule(groupID: ScheduleRequest): Requests<List<Lesson>>{
        return when (val result = source.getSchedule(groupID)) {
            is Requests.Success -> {
                Requests.Success(
                    result.data.map{it.toLesson()}
                )
            }
            is Requests.Error -> {
                Requests.Error(
                    result.exception
                )
            }
        }
    }
}