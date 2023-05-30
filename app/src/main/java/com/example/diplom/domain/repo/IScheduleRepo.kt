package com.example.diplom.domain.repo

import com.example.diplom.domain.Requests
import com.example.diplom.domain.entity.Lesson
import com.example.diplom.domain.entity.ScheduleRequest

interface IScheduleRepo {
    suspend fun getSchedule(groupID:ScheduleRequest):Requests<List<Lesson>>
}