package com.example.youbank.room

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.youbank.room.models.RoomAccount
import com.example.youbank.room.repos.AccountRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AccountViewModel(application: Application): AndroidViewModel(application) {

    val readAccounts: LiveData<List<RoomAccount>>
    private val repository: AccountRepository

    init {
        val accountDao = CustomerDatabase.getDatabase(application).accountDao()
        repository = AccountRepository(accountDao)
        readAccounts = repository.readAccounts
    }

    fun addAccount(a: RoomAccount) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addAccounts(a)
        }
    }
}