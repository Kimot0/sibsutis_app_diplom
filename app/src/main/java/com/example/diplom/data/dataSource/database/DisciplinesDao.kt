package com.example.diplom.data.dataSource.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.diplom.domain.entity.DisciplineDbEntity

@Dao
interface DisciplinesDao {

    @Insert
    suspend fun saveDiscipline(discipline: DisciplineDbEntity)

    @Query("DELETE FROM disciplines")
    suspend fun clearDisciplines()

    @Query("SELECT * FROM disciplines")
    suspend fun getDisciplines(): List<DisciplineDbEntity>

    @Query("SELECT DISTINCT lessonType FROM disciplines WHERE discipline = :discipline")
    suspend fun getDisciplineTypesByName(discipline: String): List<String>?

    @Query("SELECT DISTINCT teacherFIO FROM disciplines WHERE discipline = :discipline and lessonType =:lessonType ")
    suspend fun getTecherforlessonType(discipline: String, lessonType: String): String?
}