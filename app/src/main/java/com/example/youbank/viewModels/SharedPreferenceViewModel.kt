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

    fun getCustomerIdInSp(): Int {
        return sp.getInt("customerId")
    }

    fun getPasswordInSp(): String? {
        return sp.getString("password", null)
    }

    fun getNameInSp(): String? {
        return sp.getString("name", null)
    }

    fun saveBiometricUseStatus(b: Boolean) {
        sp.saveBoolean("biometricUseStatus", b)
    }

    fun getBiometricUseStatus(): Boolean {
        return sp.getBoolean("biometricUseStatus", false)
    }

    fun clearSharedPreferences() {
        sp.clearSharedPreference()
    }

}