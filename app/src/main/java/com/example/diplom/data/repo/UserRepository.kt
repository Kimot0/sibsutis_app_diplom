package com.example.diplom.data.repo

import com.example.diplom.data.dataSource.SibsutisRemoteDataSource
import com.example.diplom.data.dataSource.database.DisciplinesDao
import com.example.diplom.data.remote.entity.ApiDiscipline
import com.example.diplom.data.remote.entity.ApiStudentOfGroup
import com.example.diplom.data.remote.entity.toDisciplinesDbEntity
import com.example.diplom.data.remote.entity.toStudentOfGroupDbEntity
import com.example.diplom.data.remote.entity.toUserAuthResult
import com.example.diplom.domain.entity.UserAuthRequest
import com.example.diplom.domain.entity.UserAuthResult
import com.example.diplom.domain.repo.IGroupRepo
import com.example.diplom.domain.repo.IUserDbRepo
import com.example.diplom.domain.repo.IUserRepo

class UserRepository(
    private val source: SibsutisRemoteDataSource,
    private val userDbRepository: IUserDbRepo,
    private val groupRepository: IGroupRepo,
    private val disciplinesDao: DisciplinesDao
) : IUserRepo {

    override suspend fun auth(user: UserAuthRequest): UserAuthResult {
        return source.auth(user).toUserAuthResult()
    }

    override suspend fun clearLoggedUser() {
        userDbRepository.clearUser()
    }

    override suspend fun saveLoggedUser(user: UserAuthResult) {
        userDbRepository.saveUser(user)
    }

    override suspend fun getGroup(group: String): List<ApiStudentOfGroup> {
        val groupList = source.getGroup(group)
        groupRepository.clearStudents()
        groupList.forEach {
            groupRepository.saveStudent(it.toStudentOfGroupDbEntity(group))
        }
        return groupList
    }
}