package com.example.youbank.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.youbank.sharedPreferences.SharedPreference

class SharedPreferenceViewModel(application: Application): AndroidViewModel(application) {

    val sp: SharedPreference = SharedPreference(application)

    fun saveCustomerIdInSp(id: Int) {
        sp.saveInt("customerId", id)
    }

    fun getCustomerIdInSp(): Int {
        return sp.getInt("customerId")
    }

    fun savePasswordInSp(password: String) {
        sp.saveString("password", password)
    }

    fun getPasswordInSp(): String? {
        return sp.getString("password", null)
    }

    fun saveNameInSp(name: String) {
        sp.saveString("name", name)
    }

    fun getNameInSp(): String? {
        return sp.getString("name", null)
    }

    fun removeSpValue(KEY_NAME: String) {
        sp.removeValue(KEY_NAME)
    }

    fun clearSharedPreferences() {
        sp.clearSharedPreference()
    }

}