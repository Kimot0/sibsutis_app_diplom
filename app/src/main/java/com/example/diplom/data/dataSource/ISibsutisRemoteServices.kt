package com.example.diplom.data.dataSource

import com.example.diplom.data.remote.entity.ApiUser
import com.example.diplom.data.remote.network.INetwork
import com.example.diplom.domain.entity.UserAuthRequest
import retrofit2.http.Body
import retrofit2.http.POST
import java.util.UUID

fun provideSibsutisServices(network: INetwork): ISibsutisRemoteServices =
    network.retrofit.create(
        ISibsutisRemoteServices::class.java
    )

interface ISibsutisRemoteServices{
    @POST("login")
    suspend fun auth(@Body userAuth: UserAuthRequest): ApiUser
}
