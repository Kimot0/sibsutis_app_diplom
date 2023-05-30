package com.example.diplom.data.repo

import com.example.diplom.domain.entity.UserAuthResult
import com.example.diplom.domain.repo.IUserDbRepo

class UserDbRepository(
    private val
): IUserDbRepo {
    override suspend fun saveUser(user: UserAuthResult) {
        TODO("Not yet implemented")
    }
}