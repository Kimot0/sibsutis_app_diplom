package com.example.diplom.data.dataSource.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.diplom.data.remote.entity.ApiDbListForTeacher
import com.example.diplom.domain.entity.TeacherGetGroupListRequest

@Dao
interface ListForTeacherDao {

    @Query("Select * from listforteacher WHERE teacherFIO =:teacherFIO")
    suspend fun getAllListForTeacher(teacherFIO: String):List<ApiDbListForTeacher>

    @Upsert
    suspend fun insertAllListForTeacher(ListForTeacher: ApiDbListForTeacher)

    @Query("Delete from listforteacher")
    suspend fun deleteOldListForTeacher()
}