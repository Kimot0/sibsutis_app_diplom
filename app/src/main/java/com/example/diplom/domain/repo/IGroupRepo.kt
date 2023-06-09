package com.example.diplom.domain.repo

import com.example.diplom.domain.entity.StudentOfGroup
import com.example.diplom.domain.entity.StudentsOfGroupDbEntity

interface IGroupRepo {
    suspend fun saveStudent(student: StudentsOfGroupDbEntity)
    suspend fun clearStudents()
    suspend fun getGroupFromDb(): List<StudentOfGroup>
}