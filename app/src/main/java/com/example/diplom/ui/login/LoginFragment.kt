package com.example.diplom.ui.login

import android.os.Bundle
import android.util.Log
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
import com.example.diplom.utils.Status
import com.example.diplom.utils.collectOnStart
import com.example.diplom.utils.showLongToast
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
        collectOnStart(viewModel.authState) { event ->
            when (event.status) {
                Status.LOADING -> {
                    showLoading()
                }

                Status.SUCCESS -> {
                    showContent()
                    onLoginSuccess(event.data)
                }

                Status.ERROR -> {
                    showContent()
                    requireContext().showLongToast("Ошибка входа")
                }
            }
        }
    }

    private fun bindUi() {
        with(binding) {
            LoginButton.setOnClickListener {
                login()
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

    private fun onLoginSuccess(user: UserAuthResult?) {
        when (user?.role) {
            Role.STUDENT -> {
                (requireActivity() as NavigationActivity).setupBottomNavigationBarForStudent()
            }

            Role.TEACHER -> {

                (requireActivity() as NavigationActivity).setupBottomNavigationBarForTeacher()
            }

            Role.HEAD -> {
                (requireActivity() as NavigationActivity).setupBottomNavigationBarForHead()
            }

            else -> {

            }
        }
        findNavController().navigate(R.id.action_navigation_login_to_navigation_news)
    }

    private fun showLoading() {
        binding.loader.visibility = View.VISIBLE
    }

    private fun showContent() {
        binding.loader.visibility = View.GONE
    }
}