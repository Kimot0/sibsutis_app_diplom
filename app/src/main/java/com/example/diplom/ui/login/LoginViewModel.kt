package com.example.diplom.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.diplom.domain.Requests
import com.example.diplom.domain.entity.Role
import com.example.diplom.domain.entity.UserAuthRequest
import com.example.diplom.domain.entity.UserAuthResult
import com.example.diplom.domain.repo.IUserRepo
import com.example.diplom.utils.Event
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch

class LoginViewModel(
    private val userRepository: IUserRepo
) : ViewModel() {

    private val _authState: MutableStateFlow<Event<UserAuthResult>?> = MutableStateFlow(null)
    val authState = _authState.asStateFlow().filterNotNull()

    fun auth(login: String, password: String) {
        viewModelScope.launch {
            try {
                _authState.value = Event.loading()
                val userAuthResult = userRepository.auth(UserAuthRequest(login, password))
                userRepository.clearLoggedUser()
                userRepository.saveLoggedUser(userAuthResult)
                if (userAuthResult.role == Role.HEAD) {
                    userAuthResult.group?.let { userRepository.getGroup(it) }
                }
                _authState.value = Event.success(userAuthResult)
            } catch (e: Exception) {
                _authState.value = Event.error(e)
            }
        }
    }
}
