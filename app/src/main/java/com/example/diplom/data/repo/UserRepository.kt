package com.example.diplom.data.repo

import com.example.diplom.data.dataSource.SibsutisRemoteDataSource
import com.example.diplom.data.remote.entity.toUserAuthResult
import com.example.diplom.domain.Requests
import com.example.diplom.domain.entity.UserAuthRequest
import com.example.diplom.domain.entity.UserAuthResult
import com.example.diplom.domain.repo.IUserRepository

class UserRepository(private val source: SibsutisRemoteDataSource) : IUserRepository {

    override suspend fun auth(user: UserAuthRequest): Requests<UserAuthResult> {
        return when (val result = source.auth(user)) {
            is Requests.Success -> {
                Requests.Success(
                    result.data.toUserAuthResult()
                )
            }
            is Requests.Error -> {
                Requests.Error(
                    result.exception
                )
            }
        }
    }
}