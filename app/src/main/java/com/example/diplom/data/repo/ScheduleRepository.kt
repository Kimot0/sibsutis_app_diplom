package com.example.diplom.data.repo

import com.example.diplom.data.dataSource.SibsutisRemoteDataSource
import com.example.diplom.data.dataSource.database.AppDatabase
import com.example.diplom.data.remote.entity.ApiDbSchedule
import com.example.diplom.data.remote.entity.toApiDbLesson
import com.example.diplom.data.remote.entity.toLesson
import com.example.diplom.domain.entity.Lesson
import com.example.diplom.domain.entity.ScheduleRequest
import com.example.diplom.domain.repo.IScheduleRepository

class ScheduleRepository(
    private val source: SibsutisRemoteDataSource,
    private val database: AppDatabase
) : IScheduleRepository {

    override suspend fun getScheduleFromDatabaseByWeekDay(
        groupID: String,
        weekDay: String
    ): List<Lesson>? =
        database.getScheduleDao().getGroupScheduleByWeekDay(groupID, weekDay)
            ?.sortedBy { it.lessonTime }?.map { it.toLesson() }

    override suspend fun getSchedule(groupID: ScheduleRequest): List<Lesson>? {
        val databaseSchedule = getScheduleFromDatabase(groupID.groupID)
        return databaseSchedule.ifEmpty {
            val result = source.getSchedule(groupID)
            return result?.map {
                database.getScheduleDao().insertScheduleInDatabase(it.toApiDbLesson())
                it.toLesson()
            }
        }
    }

    override suspend fun getScheduleFromDatabase(groupID: String): List<Lesson> {
        return database.getScheduleDao().getGroupSchedule(groupID).map { it.toLesson() }
    }

    override suspend fun insertScheduleInDatabase(lessonList: List<ApiDbSchedule>) {
        lessonList.forEach {
            database.getScheduleDao().insertScheduleInDatabase(it)
        }
    }
}