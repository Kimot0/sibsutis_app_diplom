package com.example.diplom.data.repo

import com.example.diplom.data.dataSource.SibsutisRemoteDataSource
import com.example.diplom.data.dataSource.database.AppDatabase
import com.example.diplom.data.remote.entity.ApiDbScheduleGroup
import com.example.diplom.data.remote.entity.toApiDbScheduleGroup
import com.example.diplom.data.remote.entity.toScheduleGroup
import com.example.diplom.data.remote.entity.toScheduleGroups
import com.example.diplom.domain.entity.ScheduleGroups
import com.example.diplom.domain.repo.IScheduleGroupsRepository

class ScheduleGroupRepository(
    private val source: SibsutisRemoteDataSource,
    private val database: AppDatabase
) : IScheduleGroupsRepository{
    override suspend fun getGroups(): List<ScheduleGroups>? {
        val databaseGroups = getGroupsFromDatabase()
        return databaseGroups.ifEmpty {
            val result = source.getScheduleGroups()
            return result?.map {
                database.getScheduleGroupsDao().insertGroupInDatabase(it.toApiDbScheduleGroup())
                it.toScheduleGroups()
            }
        }
    }

    override suspend fun getGroupsFromDatabase(): List<ScheduleGroups> {
        return database.getScheduleGroupsDao().getGroupsListFromDatabase().map{
            it.toScheduleGroup()
        }
    }

    override suspend fun insertGroupsInDatabase(groupsList: List<ApiDbScheduleGroup>) {
        groupsList.forEach{
            database.getScheduleGroupsDao().insertGroupInDatabase(it)
        }
    }
}