package com.example.diplom.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.diplom.domain.Requests
import com.example.diplom.domain.entity.UserAuthRequest
import com.example.diplom.domain.entity.UserAuthResult
import com.example.diplom.domain.repo.IUserRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val userRepository: IUserRepo
) : ViewModel() {

    private val _authState: MutableStateFlow<Requests<UserAuthResult>?> = MutableStateFlow(null)
    val authState: StateFlow<Requests<UserAuthResult>?> = _authState

    fun auth(login: String, password: String) {
        viewModelScope.launch {
            _authState.value = userRepository.auth(UserAuthRequest(login, password))
        }
    }

    fun saveUser(user: UserAuthResult) {
        viewModelScope.launch {

        }
    }
}
