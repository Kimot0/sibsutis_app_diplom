package com.example.diplom.domain.repo

import com.example.diplom.data.remote.entity.ApiDbScheduleGroup
import com.example.diplom.domain.entity.ScheduleGroups

interface IScheduleGroupsRepository {
    suspend fun getGroups():List<ScheduleGroups>?
    suspend fun getGroupsFromDatabase():List<ScheduleGroups>?
    suspend fun insertGroupsInDatabase(groupsList:List<ApiDbScheduleGroup>)
}