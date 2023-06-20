package com.example.diplom.data.repo

import com.example.diplom.data.dataSource.SibsutisRemoteDataSource
import com.example.diplom.data.dataSource.database.DisciplinesDao
import com.example.diplom.data.remote.entity.ApiDiscipline
import com.example.diplom.data.remote.entity.toDisciplinesDbEntity
import com.example.diplom.domain.entity.DisciplineDbEntity
import com.example.diplom.domain.repo.IDisciplineRepo

class DisciplinesRepository(
    private val source: SibsutisRemoteDataSource,
    private val disciplinesDao: DisciplinesDao
) : IDisciplineRepo {

    override suspend fun getDisciplinesFromRemote(group: String): List<ApiDiscipline> {
        val disciplines = source.getDisciplines(group)
        disciplinesDao.clearDisciplines()
        disciplines.forEach { apiDiscipline ->
            disciplinesDao.saveDiscipline(apiDiscipline.toDisciplinesDbEntity())
        }
        return disciplines
    }

    override suspend fun getDisciplinesFromDb(): List<DisciplineDbEntity> {
        return disciplinesDao.getDisciplines()
    }

    override suspend fun saveDiscipline(disciplineDbEntity: DisciplineDbEntity) {
        disciplinesDao.saveDiscipline(disciplineDbEntity)
    }

    override suspend fun clearDisciplines() {
        disciplinesDao.clearDisciplines()
    }

    override suspend fun getDisciplineTypesByName(discipline: String): List<String>? {
        return disciplinesDao.getDisciplineTypesByName(discipline)
    }

    override suspend fun getTeacherFIOfromDB(discipline: String, lessonType: String): String? {
        return disciplinesDao.getTecherforlessonType(discipline, lessonType)
    }
}