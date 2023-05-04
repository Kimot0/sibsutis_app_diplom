package com.example.diplom.data.dataSource

import com.example.diplom.data.remote.network.safeApiCall
import com.example.diplom.domain.entity.UserAuthRequest

class SibsutisRemoteDataSource(private val api: ISibsutisRemoteServices) {
    suspend fun auth(user : UserAuthRequest) = safeApiCall { api.auth(user) }
}