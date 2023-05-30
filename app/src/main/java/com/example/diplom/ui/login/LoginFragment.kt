package com.example.diplom.ui.login

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.diplom.NavigationActivity
import com.example.diplom.R
import com.example.diplom.data.dataSource.database.InMemoryCache
import com.example.diplom.databinding.LoginFragmentBinding
import com.example.diplom.domain.entity.Account
import com.example.diplom.domain.entity.ScheduleRequest
import com.example.diplom.domain.entity.UserAuthRequest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

//TODO : Create security algorithm for encrypting password and login

//TODO 2: Create token auth to stop login everytime we start app

class LoginFragment : Fragment(R.layout.login_fragment) {

    private lateinit var binding: LoginFragmentBinding
    private val model: LoginViewModel by viewModel()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = LoginFragmentBinding.bind(view)
        bindUi()
    }

    private fun bindUi() {
        with(binding) {
            LoginButton.setOnClickListener{
                it.isClickable = false
                lifecycleScope.launch {
                    login()
                    it.isClickable = true
                }
            }
        }
    }

    private suspend fun login() {
        with(binding) {
            val role = "TEACHER"
            val login = loginEditText.text.toString()
            val pass = passwordEditText.text.toString()
            if (logs.contains(login) && passwords.contains(pass)) {
                Toast.makeText(
                    context,
                    "Your login: $login \nyour password: $pass",
                    Toast.LENGTH_SHORT
                ).show()
                // TODO: Получать данные с сервера
                when (role) {
                    "TEACHER" -> {
                        (requireActivity() as NavigationActivity).setupBottomNavigationBarForTeacher()
                    }
                    "STUDENT" -> {
                        (requireActivity() as NavigationActivity).setupBottomNavigationBarForStudent()
                    }
                    "HEAD" -> {
                        (requireActivity() as NavigationActivity).setupBottomNavigationBarForHead()
                    }
                }
                findNavController().navigate(R.id.action_navigation_login_to_navigation_news)
            } else {
                Toast.makeText(context, "ERROR", Toast.LENGTH_SHORT).show()
            }
        }
    }
}