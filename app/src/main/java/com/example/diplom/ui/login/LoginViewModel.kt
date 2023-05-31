package com.example.diplom.ui.login

import androidx.lifecycle.ViewModel
import com.example.diplom.domain.Requests
import com.example.diplom.domain.entity.UserAuthRequest
import com.example.diplom.domain.repo.IUserRepository

class LoginViewModel(
    private val loginRepository: IUserRepository
) : ViewModel() {
    lateinit var groupRes:String
    suspend fun auth(user: UserAuthRequest): Int {
        return when (val result = loginRepository.auth(user)) {
            is Requests.Success -> {
                groupRes = result.data.group
                1
            }

            is Requests.Error -> {
                0
            }
        }

    }
}
