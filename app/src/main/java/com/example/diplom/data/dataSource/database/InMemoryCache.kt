package com.example.diplom.data.dataSource.database

import com.example.diplom.domain.entity.Account
import com.example.diplom.domain.entity.Lesson
import com.example.diplom.domain.entity.ScheduleRequest

object InMemoryCache {
    lateinit var user:Account
    lateinit var group: ScheduleRequest
    var groupSchedule:MutableList<MutableList<Lesson>> = mutableListOf()
}