package com.example.youbank.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.youbank.sharedPreferences.SharedPreference

class SharedPreferenceViewModel(application: Application): AndroidViewModel(application) {

    private val sp: SharedPreference = SharedPreference(application)

    fun saveCustomerInSp(id: Int, password: String, name: String) {
        sp.saveInt("customerId", id)
        sp.saveString("password", password)
        sp.saveString("name", name)
    }

    // TODO - Delete once 4 digit password is posted together with customer
    fun saveCustomerIdInSp(id: Int) {
        sp.saveInt("customerId", id)
    }

    fun getCustomerIdInSp(): Int {
        return sp.getInt("customerId")
    }

    // TODO - Delete once 4 digit password is posted together with customer
    fun savePasswordInSp(password: String) {
        sp.saveString("password", password)
    }

    fun getPasswordInSp(): String? {
        return sp.getString("password", null)
    }

    fun getNameInSp(): String? {
        return sp.getString("name", null)
    }

    fun clearSharedPreferences() {
        sp.clearSharedPreference()
    }

}