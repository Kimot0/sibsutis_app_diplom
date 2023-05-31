package com.example.diplom.domain.repo

import com.example.diplom.data.remote.entity.ApiDbSchedule
import com.example.diplom.domain.entity.Lesson
import com.example.diplom.domain.entity.ScheduleRequest

interface IScheduleRepository {
    suspend fun getScheduleFromDatabaseByWeekDay(groupID:String,weekDay:String): List<Lesson>?
    suspend fun getSchedule(groupID:ScheduleRequest):List<Lesson>?
    suspend fun getScheduleFromDatabase(groupID:String):List<Lesson>
    suspend fun insertScheduleInDatabase(lessonList:List<ApiDbSchedule>)
}