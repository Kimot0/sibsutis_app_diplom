package com.example.diplom.data.dataSource.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.diplom.data.remote.entity.ApiDbScheduleGroup

@Dao
interface ScheduleGroupsDao {

    @Upsert
    suspend fun insertGroupInDatabase(group:ApiDbScheduleGroup)

    @Query("""Select * from groupsSchedule""")
    suspend fun getGroupsListFromDatabase():List<ApiDbScheduleGroup>
}