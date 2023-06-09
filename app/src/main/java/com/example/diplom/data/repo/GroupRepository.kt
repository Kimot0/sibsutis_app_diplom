package com.example.diplom.data.repo

import com.example.diplom.data.dataSource.database.GroupDao
import com.example.diplom.domain.entity.StudentOfGroup
import com.example.diplom.domain.entity.StudentsOfGroupDbEntity
import com.example.diplom.domain.entity.toStudentOfGroup
import com.example.diplom.domain.repo.IGroupRepo

class GroupRepository(
    private val groupDao: GroupDao
) : IGroupRepo {

    override suspend fun saveStudent(student: StudentsOfGroupDbEntity) {
        groupDao.saveStudent(student)
    }

    override suspend fun clearStudents() {
        groupDao.clearStudents()
    }

    override suspend fun getGroupFromDb(): List<StudentOfGroup> {
        return groupDao.getGroupFromDb().map {
            it.toStudentOfGroup()
        }
    }
}