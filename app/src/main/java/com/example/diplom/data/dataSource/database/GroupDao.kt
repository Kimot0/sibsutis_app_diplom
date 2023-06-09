package com.example.diplom.data.dataSource.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.diplom.domain.entity.StudentsOfGroupDbEntity

@Dao
interface GroupDao {

    @Insert
    suspend fun saveStudent(student: StudentsOfGroupDbEntity)

    @Query("DELETE FROM groups")
    suspend fun clearStudents()

    @Query("SELECT * FROM groups")
    suspend fun getGroupFromDb(): List<StudentsOfGroupDbEntity>
}