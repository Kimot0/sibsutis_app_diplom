package com.example.diplom.ui.login

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.diplom.NavigationActivity
import com.example.diplom.R
import com.example.diplom.databinding.LoginFragmentBinding
import com.example.diplom.domain.Requests
import com.example.diplom.domain.entity.Role
import com.example.diplom.domain.entity.UserAuthResult
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

//TODO : Create security algorithm for encrypting password and login

//TODO 2: Create token auth to stop login everytime we start app

class LoginFragment : Fragment(R.layout.login_fragment) {

    private lateinit var binding: LoginFragmentBinding
    private val viewModel: LoginViewModel by viewModel()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = LoginFragmentBinding.bind(view)
        bindUi()
        collectData()
    }

    private fun collectData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.authState.collect {
                when (it) {
                    is Requests.Success -> {
                        onLoginSuccess(it.data)
                    }

                    else -> {

                    }
                }
            }
        }
    }

    private fun bindUi() {
        with(binding) {
            LoginButton.setOnClickListener {
                it.isClickable = false
                login()
                it.isClickable = true
            }
        }
    }

    private fun login() {
        with(binding) {
            val login = loginEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()
            viewModel.auth(login, password)
        }
    }

    private fun onLoginSuccess(user: UserAuthResult) {
        viewModel.saveUser(user)
        when (user.role) {
            Role.STUDENT -> {
                (requireActivity() as NavigationActivity).setupBottomNavigationBarForStudent()
            }

            Role.TEACHER -> {
                //TODO Подумать что делать для препода
                (requireActivity() as NavigationActivity).setupBottomNavigationBarForTeacher()
            }

            Role.HEAD -> { viewModel.getGroup(user.group) }
        }
        findNavController().navigate(R.id.action_navigation_login_to_navigation_news)
    }
}