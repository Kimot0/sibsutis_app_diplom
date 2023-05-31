package com.example.diplom.data.dataSource.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.diplom.data.remote.entity.ApiDbSchedule


@Dao
interface ScheduleDao {
    @Query("Select * from schedule where groupID = :group")
    suspend fun getGroupSchedule(group:String):List<ApiDbSchedule>

    @Upsert
    suspend fun insertScheduleInDatabase(lessonList:ApiDbSchedule)

    @Query("""SELECT * FROM schedule WHERE weekDay = :weekDay and groupID = :groupID""")
    suspend fun getGroupScheduleByWeekDay(groupID: String, weekDay: String): List<ApiDbSchedule>?

    @Query("Delete from schedule where groupID = :group")
    suspend fun deleteGroupSchedule(group: String)
}