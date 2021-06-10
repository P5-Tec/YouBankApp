package com.example.youbank.room

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.youbank.models.Account
import com.example.youbank.models.Card
import com.example.youbank.models.Customer
import com.example.youbank.retrofit.ApiService
import com.example.youbank.retrofit.CustomerService
import com.example.youbank.room.models.RoomAccount
import com.example.youbank.room.models.RoomCard
import com.example.youbank.room.models.RoomCustomer
import com.example.youbank.room.repos.CustomerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CustomerViewModel(application: Application): AndroidViewModel(application) {

    val readCustomer: LiveData<RoomCustomer>
    private val repository: CustomerRepository

    var cus = Customer()
    var a = listOf<Account>()
    var cards = listOf<Card>()

    init {
        val customerDao = CustomerDatabase.getDatabase(application).customerDao()
        repository = CustomerRepository(customerDao)
        readCustomer = repository.readCustomer
    }

    fun addCustomer(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val service: CustomerService = ApiService.buildService(CustomerService::class.java)
            val req: Call<Customer> = service.getCustomerById(id)

            req.enqueue(object: Callback<Customer> {
                override fun onResponse(call: Call<Customer>, response: Response<Customer>) {
                    cus = response.body()!! // Getting all the customer data
                    a = response.body()!!.accounts // Getting all the account data
                    cards = response.body()!!.accounts[0].cards // Getting all the card data
                    Log.d("Customer1", cus.fullName)
                    Log.d("Accounts1", a.size.toString())
                    Log.d("Cards1", cards.size.toString())
                }

                override fun onFailure(call: Call<Customer>, t: Throwable) {
                    Log.d("get customer failed", t.cause.toString())
                }
            })
        }

        Log.d("Customer2", cus.fullName)
        Log.d("Accounts2", a.size.toString())
        Log.d("Cards2", cards.size.toString())

        val roomCustomer = RoomCustomer(0, cus.customerId, cus.fullName, cus.birthday, cus.email, cus.phone, cus.address)
        val roomAccount = RoomAccount(0, a[0].accountId, a[0].accountNumber, a[0].accountType, a[0].balance)
        val roomCard = RoomCard(
            0, cards[0].cardId, cards[0].cardNumber, cards[0].ccv, cards[0].expirationDate, cards[0].cardType,
            cards[0].cardStatus)
        // Adding customer to roomdatabase
        repository.addCustomer(roomCustomer)
    }

}