package com.example.diplom.data.repo

import com.example.diplom.data.dataSource.SibsutisRemoteDataSource
import com.example.diplom.data.dataSource.database.AppDatabase
import com.example.diplom.data.remote.entity.*
import com.example.diplom.domain.entity.HeadListForTeacher
import com.example.diplom.domain.entity.TeacherAttendance
import com.example.diplom.domain.entity.TeacherGetGroupListRequest
import com.example.diplom.domain.repo.IGetHeadListRepo

class GetHeadListRepo (
    private val source: SibsutisRemoteDataSource,
    private val database: AppDatabase,
        ) : IGetHeadListRepo {
    override suspend fun getGetHeadList(teacherFIO:TeacherGetGroupListRequest): List<HeadListForTeacher> {
        val databaseResult = getAllListForTeacher(teacherFIO)
        return databaseResult.ifEmpty {
            val result = source.getGroupListForTeacher (teacherFIO)
            return result.map {
                insertAllListForTeacher(it.toApiDB())
                it.toGetHeadList()
            }
        }
    }

    override suspend fun getAllListForTeacher(teacherFIO: TeacherGetGroupListRequest): List<HeadListForTeacher> {
        return database.getListForTeacher().getAllListForTeacher(teacherFIO.teacherFIO).map { it.toGetHeadListForTeacher() }
    }

    override suspend fun insertAllListForTeacher(item: ApiDbListForTeacher) {
        database.getListForTeacher().insertAllListForTeacher(item)
    }

    override suspend fun deleteOldListForTeacher() {
        database.getListForTeacher().deleteOldListForTeacher()
    }

    override suspend fun sendGroupListByTeacher(list: HeadListForTeacher): String {
        return source.sendGroupListByTeacher(list)
    }

}