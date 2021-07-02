package com.example.youbank.viewModels.listViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.youbank.models.Account
import com.example.youbank.room.CustomerDatabase
import com.example.youbank.room.daos.AccountDao
import com.example.youbank.room.repos.AccountRepository

class AccountListViewmodel(application: Application): AndroidViewModel(application) {

    private val accountDao: AccountDao = CustomerDatabase.getDatabase(application, viewModelScope).accountDao()
    val repository = AccountRepository(accountDao)
    val allAccounts: LiveData<List<Account>> = repository.getallAccounts().asLiveData()
}