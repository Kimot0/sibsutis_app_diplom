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
import com.example.diplom.domain.entity.Account
import com.example.diplom.domain.entity.UserAuthRequest
import com.example.diplom.domain.entity.UserAuthResult
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.scope.Scope


class LoginFragment : Fragment(R.layout.login_fragment) {

    private lateinit var binding: LoginFragmentBinding
    private val model: LoginViewModel by viewModel()
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
                lifecycleScope.launch { login() }
            }
        }
    }

    private suspend fun login() {
        with(binding) {
            val login = loginEditText.text.toString()
            val pass = passwordEditText.text.toString()
            val user = UserAuthRequest(login, pass)
            when(model.auth(user)) {
                1 -> {
                    InMemoryCache.user = Account(0,login,model.groupRes)
                    Toast.makeText(context,"gj",Toast.LENGTH_LONG).show()
                    findNavController().navigate(R.id.action_navigation_login_to_navigation_news)
                }
                0 -> {
                    Toast.makeText(context, model.groupRes, Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}