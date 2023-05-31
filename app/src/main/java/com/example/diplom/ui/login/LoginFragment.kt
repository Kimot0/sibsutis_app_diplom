package com.example.diplom.ui.login

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.diplom.R
import com.example.diplom.data.dataSource.database.InMemoryCache
import com.example.diplom.databinding.LoginFragmentBinding
import com.example.diplom.domain.entity.ScheduleRequest
import com.example.diplom.domain.entity.UserAuthRequest
import com.example.diplom.utils.Status
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
            val login = loginEditText.text.toString()
            val pass = passwordEditText.text.toString()
            val user = UserAuthRequest(login, pass)
            viewModel.auth(user)
            collectData()
        }
    }

    private fun collectData() {
        lifecycleScope.launch {
            viewModel.loginStateFlow.collect{
                when(it.status){
                    Status.SUCCESS -> {
                        InMemoryCache.group = it.data?.let{ user -> ScheduleRequest(user.group) }!!
                        findNavController().navigate(R.id.action_navigation_login_to_navigation_news)
                    }
                    Status.ERROR -> Toast.makeText(context, "Something went wrong", Toast.LENGTH_LONG).show()
                    Status.LOADING -> Unit
                }
            }
        }
    }
}