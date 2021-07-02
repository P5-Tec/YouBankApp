package com.example.youbank.viewModels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.youbank.models.Card
import com.example.youbank.models.Customer
import com.example.youbank.retrofit.repo.LoginRepo
import com.example.youbank.retrofit.repo.RetroAccountRepository
import com.example.youbank.retrofit.repo.RetroCustomerRepo
import com.example.youbank.room.CustomerDatabase
import com.example.youbank.room.daos.AccountDao
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
    private val retroCustomerRepo: RetroCustomerRepo = RetroCustomerRepo()

    private val accountDao: AccountDao = CustomerDatabase.getDatabase(application, viewModelScope).accountDao()
    private val accountRepository: AccountRepository = AccountRepository(accountDao)
    private val retroAccountRepository: RetroAccountRepository = RetroAccountRepository()

    private val cardDao: CardDao = CustomerDatabase.getDatabase(application, viewModelScope).cardDao()
    private val cardRepository: CardRepository = CardRepository(cardDao)

    private val transactionDao: TransactionDao = CustomerDatabase.getDatabase(application, viewModelScope).transactionDao()
    private val transactionRepository: TransactionRepository = TransactionRepository(transactionDao)

    //Api calls / Room saving

    fun getAccounts() {
        viewModelScope.launch(Dispatchers.IO) {
            val req2 = retroCustomerRepo.getCustomerById(user.customerId)
            //val req = retroAccountRepository.getAccountById(user.customerId)
            accountRepository.insertMultiple(req2.accounts)
        }
    }

    fun getTransactions2(cId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val req = retroAccountRepository.getAccountById(cId)
            transactionRepository.insertMultiple(req.transactions)
        }
    }

    fun getCards2() {
        viewModelScope.launch(Dispatchers.IO) {
            val req2 = retroCustomerRepo.getCustomerById(user.customerId)
            val cardsList: MutableList<Card> = mutableListOf()
            req2.accounts.forEach {
                it.cards.forEach { cc ->
                    Log.i("card", cc.cardNumber.toString())
                    cardsList.add(cc)
                }
            }
            cardRepository.insertMultiple2(cardsList)
        }
    }

    //fix this to make 1 api call
    val loggedin = liveData(Dispatchers.IO) {
        val response = repository.getLogin(user)
        user = response
        emit(response)
    }

    fun setData(emval: String, psval: String) {
        user.email = emval
        user.password = psval
    }
}