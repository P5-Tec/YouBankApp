package com.example.youbank.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.youbank.models.Card
import com.example.youbank.models.Customer
import com.example.youbank.retrofit.repo.LoginRepository
import com.example.youbank.retrofit.repo.RetroAccountRepository
import com.example.youbank.retrofit.repo.RetrofitCustomerRepository
import com.example.youbank.room.CustomerDatabase
import com.example.youbank.room.daos.AccountDao
import com.example.youbank.room.daos.CardDao
import com.example.youbank.room.daos.TransactionDao
import com.example.youbank.room.repos.AccountRepository
import com.example.youbank.room.repos.CardRepository
import com.example.youbank.room.repos.TransactionRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class LoginViewModel(application: Application): AndroidViewModel(application) {
    private var user = Customer()

    private val loginRepo: LoginRepository = LoginRepository()

    //Api & Room stuff
    private val retrofitCustomerRepo: RetrofitCustomerRepository = RetrofitCustomerRepository()

    private val accountDao: AccountDao = CustomerDatabase.getDatabase(application).accountDao()
    private val accountRepository: AccountRepository = AccountRepository(accountDao)
    private val retroAccountRepository: RetroAccountRepository = RetroAccountRepository()

    private val cardDao: CardDao = CustomerDatabase.getDatabase(application).cardDao()
    private val cardRepository: CardRepository = CardRepository(cardDao)

    private val transactionDao: TransactionDao = CustomerDatabase.getDatabase(application).transactionDao()
    private val transactionRepository: TransactionRepository = TransactionRepository(transactionDao)

    //Api calls / Room saving

    fun getAccounts(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val req = retrofitCustomerRepo.getCustomerById(id)
            accountRepository.insertMultiple(req.accounts)
        }
    }

    fun getTransactions(cId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val req = retroAccountRepository.getAccountById(cId)
            transactionRepository.insertMultiple(req.transactions)
        }
    }

    fun getCards(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val req = retrofitCustomerRepo.getCustomerById(id)
            val cardsList: MutableList<Card> = mutableListOf()
            req.accounts.forEach {
                it.cards.forEach { cc ->
                    cardsList.add(cc)
                }
            }
            cardRepository.insertMultiple2(cardsList)
        }
    }


    val loginResponse: MutableLiveData<Response<Customer>> = MutableLiveData()

    fun login() {
        viewModelScope.launch {
            val response = loginRepo.login(user)
            loginResponse.value = response
        }
    }

    //fun doLogin() = runBlocking {
    //    val job = launch {
    //        login()
    //    }
    //    job.join()
    //}
    //
    //
    //var job: Job? = null
    //
    //private fun login(): Customer? {
    //    //response = repository.getLogin(user)
    //
    //
    //    val client: LoginService = RetrofitClient.retrofit.create(LoginService::class.java)
    //    val response = client.getLogin(user)
    //    return response.body()
    //
    //
    //
    //
    //}

    //fun canLogin(): Boolean{
    //    return response.customerId != 0
    //}


    //fix this to make 1 api call
    //val loggedin = liveData(Dispatchers.IO) {
    //    val response = repository.getLogin(user)
    //    user = response
    //    emit(response)
    //}

    fun setData(emval: String, psval: String) {
        user.email = emval
        user.password = psval
    }
}