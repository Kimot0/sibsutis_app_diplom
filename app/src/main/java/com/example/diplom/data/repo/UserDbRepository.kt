package com.example.diplom.data.repo

import com.example.diplom.data.dataSource.database.UserDao
import com.example.diplom.domain.entity.UserAuthResult
import com.example.diplom.domain.entity.toUserDbEntity
import com.example.diplom.domain.repo.IUserDbRepo

class UserDbRepository(
    private val userDao: UserDao
): IUserDbRepo {

    override suspend fun saveUser(user: UserAuthResult) {
        userDao.insertUser(user.toUserDbEntity())
    }

    override suspend fun clearUser() {
        userDao.clearUserTable()
    }
}