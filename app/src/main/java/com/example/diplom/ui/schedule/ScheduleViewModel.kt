package com.example.diplom.ui.schedule

import androidx.lifecycle.ViewModel
import com.example.diplom.data.dataSource.database.InMemoryCache
import com.example.diplom.domain.Requests
import com.example.diplom.domain.entity.Lesson
import com.example.diplom.domain.entity.News
import com.example.diplom.domain.entity.ScheduleRequest
import com.example.diplom.domain.repo.IScheduleRepo

class ScheduleViewModel(
    private val repo: IScheduleRepo
) : ViewModel() {
    val tabTitles = arrayOf(
        "ПН", "ВТ", "СР", "ЧТ", "ПТ", "СБ"
    )
    var tempList = mutableListOf<Lesson>()
    val mondayList = mutableListOf<Lesson>()
    val tuesdayList = mutableListOf<Lesson>()
    val wednesdayList = mutableListOf<Lesson>()
    val thursdayList = mutableListOf<Lesson>()
    val fridayList = mutableListOf<Lesson>()
    val saturdayList = mutableListOf<Lesson>()

    suspend fun getShedule(groupID: ScheduleRequest) {
        return when (val result = repo.getSchedule(groupID)) {
            is Requests.Success -> {
                if(InMemoryCache.groupSchedule.isEmpty())InMemoryCache.groupSchedule = mutableListOf(result.data.toMutableList())
                else{
                    InMemoryCache.groupSchedule.clear()
                    InMemoryCache.groupSchedule = mutableListOf(result.data.toMutableList())
                }
                tempList = result.data as MutableList<Lesson>
            }

            is Requests.Error -> {
                tempList = mutableListOf()
            }
        }

    }
    private fun clearStructs(){
        mondayList.clear()
        tuesdayList.clear()
        wednesdayList.clear()
        thursdayList.clear()
        fridayList.clear()
        saturdayList.clear()
        InMemoryCache.groupSchedule.clear()
    }

    fun sortSchedule(schedule: MutableList<Lesson>) {
        clearStructs()
        schedule.forEach {
            when (it.weekDay) {
                "ПН" -> {
                    mondayList.add(it)
                }

                "ВТ" -> {
                    tuesdayList.add(it)
                }

                "СР" -> {
                    wednesdayList.add(it)
                }

                "ЧТ" -> {
                    thursdayList.add(it)
                }

                "ПТ" -> {
                    fridayList.add(it)
                }

                "СБ" -> {
                    saturdayList.add(it)
                }
            }
        }
        for (day in tabTitles) {
            when (day) {
                "ПН" -> {
                    InMemoryCache.groupSchedule.add(0, mondayList)
                }

                "ВТ" -> {
                    InMemoryCache.groupSchedule.add(1, tuesdayList)
                }

                "СР" -> {
                    InMemoryCache.groupSchedule.add(2, wednesdayList)
                }

                "ЧТ" -> {
                    InMemoryCache.groupSchedule.add(3, thursdayList)
                }

                "ПТ" -> {
                    InMemoryCache.groupSchedule.add(4, fridayList)
                }

                "СБ" -> {
                    InMemoryCache.groupSchedule.add(5, saturdayList)
                }
            }
        }
        InMemoryCache.groupSchedule.forEach {
            it.sortBy {
                it.lessonTime
            }
        }

    }
}