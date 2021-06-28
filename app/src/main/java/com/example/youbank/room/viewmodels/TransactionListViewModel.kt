package com.example.youbank.room.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.example.youbank.models.Transaction
import com.example.youbank.room.CustomerDatabase
import com.example.youbank.room.daos.TransactionDao
import com.example.youbank.room.repos.TransactionRepository

class TransactionListViewModel(application: Application) : AndroidViewModel(application) {

    private val transactionDao: TransactionDao = CustomerDatabase.getDatabase(application, viewModelScope).transactionDao()
    val repository = TransactionRepository(transactionDao)
    val allTransactions: LiveData<List<Transaction>> = repository.getAllTransactions().asLiveData()

}