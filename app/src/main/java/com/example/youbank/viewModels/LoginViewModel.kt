package com.example.youbank.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.youbank.models.Customer
import com.example.youbank.retrofit.TransactionService
import com.example.youbank.retrofit.repo.LoginRepo
import com.example.youbank.retrofit.repo.RetroAccountRepository
import com.example.youbank.retrofit.repo.RetroTransactionRepository
import com.example.youbank.room.CustomerDatabase
import com.example.youbank.room.daos.AccountDao
import com.example.youbank.room.daos.AccountDao_Impl
import com.example.youbank.room.daos.CardDao
import com.example.youbank.room.daos.TransactionDao
import com.example.youbank.room.repos.AccountRepository
import com.example.youbank.room.repos.CardRepository
import com.example.youbank.room.repos.TransactionRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel(application: Application): AndroidViewModel(application) {
    private var user = Customer()

    private val repository: LoginRepo = LoginRepo()

    //Api & Room stuff
    private val accountDao: AccountDao = CustomerDatabase.getDatabase(application, viewModelScope).accountDao()
    private val accountRepository: AccountRepository = AccountRepository(accountDao)
    private val retroAccountRepository: RetroAccountRepository = RetroAccountRepository()

    private val cardDao: CardDao = CustomerDatabase.getDatabase(application, viewModelScope).cardDao()
    private val cardRepository: CardRepository = CardRepository(cardDao)
    private val retroCardRepository: RetroAccountRepository = RetroAccountRepository()

    private val transactionDao: TransactionDao = CustomerDatabase.getDatabase(application, viewModelScope).transactionDao()
    private val transactionRepository: TransactionRepository = TransactionRepository(transactionDao)
    private val retroTransactionRepository: RetroTransactionRepository = RetroTransactionRepository()

    //Api calls / Room saving



    public fun getAccounts(){
        viewModelScope.launch(Dispatchers.IO) {
            val req = retroAccountRepository.getAccountById(user.customerId)
            accountRepository.insert(req)
        }
    }

    public fun getTransactions(){
        viewModelScope.launch(Dispatchers.IO){
            val req = retroTransactionRepository.getTransactions()
            transactionRepository.insertMultiple(req)
        }
    }

    public fun getTransactions2(cId: Int){
        viewModelScope.launch(Dispatchers.IO) {
            val req = retroAccountRepository.getAccountById(cId)
            //transactionRepository.insertMultiple(req.transactions)
        }
    }


    //fix this to make 1 api call
    val loggedin = liveData(Dispatchers.IO) {
        val response = repository.getLogin(user)
        user = response
        emit(response)
    }

    fun setData(emval: String,psval: String){
        user.email = emval
        user.password = psval
    }

    fun getInfo(): String{
        return user.email + ":" + user.password
    }

    fun getPin(): String? {
        return user.pincode
    }
}