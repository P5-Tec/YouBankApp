package com.example.youbank.viewModels

import androidx.lifecycle.ViewModel

class KeypadViewModel: ViewModel() {

    fun validatePasswordPref(userInput: String, spPassword: String?): Boolean {
        return userInput == spPassword
    }

}