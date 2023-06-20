package com.example.diplom.domain.repo

import com.example.diplom.data.remote.entity.ApiDbListForTeacher
import com.example.diplom.domain.Requests
import com.example.diplom.domain.entity.HeadListForTeacher
import com.example.diplom.domain.entity.TeacherAttendance
import com.example.diplom.domain.entity.TeacherGetGroupListRequest

interface IGetHeadListRepo {

    suspend fun getGetHeadList(teacherFIO: TeacherGetGroupListRequest): List<HeadListForTeacher>

    suspend fun getAllListForTeacher(teacherFIO: TeacherGetGroupListRequest): List<HeadListForTeacher>

    suspend fun insertAllListForTeacher(item: ApiDbListForTeacher)

    suspend fun deleteOldListForTeacher()

    suspend fun sendGroupListByTeacher(list: HeadListForTeacher) : String
}