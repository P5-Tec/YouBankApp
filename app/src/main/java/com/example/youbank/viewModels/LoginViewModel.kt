package com.example.youbank.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.youbank.models.Customer
import com.example.youbank.retrofit.repo.LoginRepo
import kotlinx.coroutines.Dispatchers

class LoginViewModel: ViewModel() {
    private var user = Customer()
    private var emailinput: String = "test"
    private var passwordinput: String = ""

    val repository: LoginRepo = LoginRepo()

    val loggedin = liveData(Dispatchers.IO) {
        val response = repository.getLogin(user)
        emit(response)
    }

    fun setEmail(emval: String) {
        user.email = emval
    }

    fun setPass(psval: String) {
        user.password = psval
    }
}