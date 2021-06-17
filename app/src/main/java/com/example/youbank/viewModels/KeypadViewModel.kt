package com.example.youbank.viewModels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.youbank.models.Customer
import com.example.youbank.retrofit.ApiService
import com.example.youbank.retrofit.CustomerService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.CoroutineContext

class KeypadViewModel: ViewModel(), CoroutineScope {

    private val service: CustomerService = ApiService.buildService(CustomerService::class.java)
    //private val req: Call<Customer> = service.getCustomerById(14)
    private lateinit var c: Customer
    private val cName = MutableLiveData<String>()

    private val job: Job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    //
    //init {
    //    launch {
    //        req.enqueue(object: Callback<Customer> {
    //            override fun onResponse(call: Call<Customer>, response: Response<Customer>) {
    //                if (response.isSuccessful) {
    //                    c.fullName = response.body()!!.fullName
    //                    setName(c.fullName)
    //                }
    //            }
    //
    //            override fun onFailure(call: Call<Customer>, t: Throwable) {
    //                setName("Hans Pilgård")
    //                Log.i("ApiNameFail", t.message.toString())
    //            }
    //        })
    //    }
    //}

    fun getName(): MutableLiveData<String> {
        return cName
    }

    fun setName(newName: String) {
        cName.value = newName
    }
}