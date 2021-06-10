package com.example.youbank.viewModels

import android.util.Log
import androidx.lifecycle.*
import com.example.youbank.models.Customer
import com.example.youbank.retrofit.repo.LoginRepo
import kotlinx.coroutines.Dispatchers
import java.io.StringReader

class LoginViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    private var user = Customer()
    private var emailinput: String = "test"
    private var passwordinput: String = ""

    val repository: LoginRepo = LoginRepo()

    val loggedin = liveData(Dispatchers.IO){
        val response = repository.getLogin(user)
        emit(response)
    }

    fun setEmail(emval: String){
        user.email = emval.toString()
    }

    fun setPass(psval: String){
        user.password = psval.toString()
    }

    fun getB() : String{
        Log.i("dd", user.birthday)
        return user.birthday
    }
}