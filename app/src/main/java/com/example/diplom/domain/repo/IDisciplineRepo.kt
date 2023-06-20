package com.example.diplom.domain.repo

import com.example.diplom.data.remote.entity.ApiDiscipline
import com.example.diplom.domain.entity.DisciplineDbEntity

interface IDisciplineRepo {
    suspend fun getDisciplinesFromRemote(group: String): List<ApiDiscipline>
    suspend fun getDisciplinesFromDb(): List<DisciplineDbEntity>
    suspend fun saveDiscipline(disciplineDbEntity: DisciplineDbEntity)
    suspend fun clearDisciplines()

    suspend fun getDisciplineTypesByName(discipline: String): List<String>?
    suspend fun getTeacherFIOfromDB(discipline: String, lessonType: String): String?
}