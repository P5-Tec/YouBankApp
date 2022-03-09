package com.example.youbank.viewModels.listViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.example.youbank.models.Transaction
import com.example.youbank.room.CustomerDatabase
import com.example.youbank.room.daos.TransactionDao
import com.example.youbank.room.repos.TransactionRepository

class TransactionListViewModel(application: Application): AndroidViewModel(application) {

    private val transactionDao: TransactionDao = CustomerDatabase.getDatabase(application).transactionDao()
    val repository = TransactionRepository(transactionDao)
    val allTransactions: LiveData<List<Transaction>> = repository.getAllTransactions().asLiveData()
}