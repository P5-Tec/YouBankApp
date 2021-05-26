package com.example.youbank.viewModels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.youbank.models.Customer
import com.example.youbank.retrofit.ApiService
import com.example.youbank.retrofit.CustomerService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class KeypadViewModel : ViewModel() {

    private val service: CustomerService = ApiService.buildService(CustomerService::class.java)
    private val req: Call<Customer> = service.getCustomerById(8)

    val cName = MutableLiveData<String>()

    init {
        req.enqueue(object : Callback<Customer> {
            override fun onResponse(call: Call<Customer>, response: Response<Customer>) {

                Log.i("idk", response.code().toString())
                if (response.isSuccessful) {
                    setName(response.body() !!.fullName)
                    Log.i("TestSucces", cName.value.toString())
                }
            }

            override fun onFailure(call: Call<Customer>, t: Throwable) {

                setName("Hans Pilgård")
                Log.i("TestFail", t.message.toString())
            }
        })

    }

    fun getName(): MutableLiveData<String> {
        return cName
    }

    fun setName(newName: String) {
        cName.value = newName
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("Keypad", "KeypadViewmodel destroyed")
    }
}