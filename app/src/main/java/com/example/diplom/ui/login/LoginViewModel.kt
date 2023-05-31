package com.example.diplom.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.diplom.domain.entity.UserAuthRequest
import com.example.diplom.domain.entity.UserAuthResult
import com.example.diplom.domain.repo.IUserRepository
import com.example.diplom.utils.Event
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginRepository: IUserRepository
) : ViewModel() {
    private val _loginStateFlow = MutableStateFlow<Event<UserAuthResult>?>(null)
    var loginStateFlow = _loginStateFlow.asStateFlow().filterNotNull()

    fun auth(user: UserAuthRequest) {
        viewModelScope.launch {
            try {
                _loginStateFlow.emit(Event.loading())
                val result = loginRepository.auth(user)
                _loginStateFlow.emit(Event.success(result))
            } catch (e: Exception) {
                _loginStateFlow.emit(Event.error(e))
            }
        }
    }
}
