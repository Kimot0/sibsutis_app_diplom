package com.example.diplom.ui.login

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.diplom.NavigationActivity
import com.example.diplom.R
import com.example.diplom.databinding.LoginFragmentBinding


class LoginFragment : Fragment(R.layout.login_fragment) {

    private var logs: MutableList<String> = mutableListOf(
        "admin", "nmoniev@gmail.com", "egorbauer@yandex.ru"
    )
    private var passwords: MutableList<String> = mutableListOf(
        "12345", "qwerty", "warthunderbaby"
    )

    private lateinit var binding: LoginFragmentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = LoginFragmentBinding.bind(view)
        bindUi()
    }

    private fun bindUi() {
        with(binding) {
            LoginButton.setOnClickListener() {
                login()
            }
        }
    }

    private fun login() {
        with(binding) {
            val role = "HEAD"
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