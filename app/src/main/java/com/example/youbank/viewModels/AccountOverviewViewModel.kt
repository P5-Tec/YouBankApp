package com.example.youbank.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.youbank.models.Account
import com.example.youbank.models.AccountWithTransactions
import com.example.youbank.room.CustomerDatabase
import com.example.youbank.room.repos.AccountRepository

class AccountOverviewViewModel(application: Application): AndroidViewModel(application) {

    var readAccount: LiveData<Account>
    lateinit var newTest: LiveData<List<AccountWithTransactions>>
    private val accountRepo: AccountRepository

    init {
        val accountDao = CustomerDatabase.getDatabase(application).accountDao()
        accountRepo = AccountRepository(accountDao)

        readAccount = accountRepo.readAccounts

    }

    fun getAccount(aId: Int) : LiveData<List<AccountWithTransactions>>{
        return accountRepo.getAccountById(aId).asLiveData(viewModelScope.coroutineContext)
    }

}