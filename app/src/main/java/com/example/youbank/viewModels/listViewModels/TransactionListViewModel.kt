package com.example.youbank.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.youbank.models.Transaction
import com.example.youbank.room.CustomerDatabase
import com.example.youbank.room.daos.TransactionDao
import com.example.youbank.room.repos.TransactionRepository

class TransactionListViewModel(application: Application): AndroidViewModel(application) {

    private val transactionDao: TransactionDao = CustomerDatabase.getDatabase(application, viewModelScope).transactionDao()
    val repository = TransactionRepository(transactionDao)
    val allTransactions: LiveData<List<Transaction>> = repository.getAllTransactions().asLiveData()
}