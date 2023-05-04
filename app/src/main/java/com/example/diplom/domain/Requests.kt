package com.example.diplom.domain

import com.example.diplom.data.remote.network.NetworkErrors

sealed class Requests<out R> {

    data class Success<out T>(val data: T) : Requests<T>()
    data class Error<out T>(val exception: NetworkErrors) : Requests<T>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
        }
    }
}

val Requests<*>.succeeded
    get() = this is Requests.Success && data != null