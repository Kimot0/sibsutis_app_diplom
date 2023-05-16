package com.example.diplom.ui.schedule

import androidx.lifecycle.ViewModel
import com.example.diplom.data.dataSource.database.InMemoryCache
import com.example.diplom.domain.Requests
import com.example.diplom.domain.entity.Lesson
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
                InMemoryCache.groupSchedule = mutableListOf(result.data.toMutableList())
                tempList = result.data as MutableList<Lesson>
            }

            is Requests.Error -> {
                tempList = mutableListOf()
            }
        }

    }

    fun sortSchedule(schedule: MutableList<Lesson>) {
        schedule.forEach {
            when (it.weekDay) {
                "ПН" -> {
                    mondayList.add(mondayList.lastIndex + 1, it)
                }

                "ВТ" -> {
                    tuesdayList.add(tuesdayList.lastIndex + 1, it)
                }

                "СР" -> {
                    wednesdayList.add(wednesdayList.lastIndex + 1, it)
                }

                "ЧТ" -> {
                    thursdayList.add(thursdayList.lastIndex + 1, it)
                }

                "ПТ" -> {
                    fridayList.add(fridayList.lastIndex + 1, it)
                }

                "СБ" -> {
                    saturdayList.add(saturdayList.lastIndex + 1, it)
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