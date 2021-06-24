package com.example.youbank.p_ExampleViewmodels

import android.app.Application
import androidx.lifecycle.*
import com.example.youbank.room.daos.AccountDao
import com.example.youbank.room.repos.AccountRepository

import com.example.youbank.room.CustomerDatabase

import com.example.youbank.room.repos.CustomerRepository
import com.example.youbank.room.daos.CustomerDao

import com.example.youbank.room.daos.TransactionDao
import com.example.youbank.room.repos.TransactionRepository

import com.example.youbank.models.AccountWithTransactions
import com.example.youbank.models.CustomerWithAccounts

import com.example.youbank.retrofit.repo.RetroTransactionRepository
import com.example.youbank.retrofit.repo.RetroAccountRepository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CustomerOverviewViewModel(application: Application) : ViewModel() {
    val acdao: AccountDao = CustomerDatabase.getDatabase(application, viewModelScope).accountDao()
    val acrepository = AccountRepository(acdao)
    val apirepository: RetroAccountRepository = RetroAccountRepository()

    val codao: CustomerDao = CustomerDatabase.getDatabase(application, viewModelScope).customerDao()
    val corepo = CustomerRepository(codao)


    val trdao: TransactionDao = CustomerDatabase.getDatabase(application, viewModelScope).transactionDao()
    val trRepository = TransactionRepository(trdao)
    val tapirepository: RetroTransactionRepository = RetroTransactionRepository()


    val customerLive: LiveData<List<CustomerWithAccounts>> = corepo.getAllInfo(21).asLiveData()

    val transactionsLive: LiveData<List<AccountWithTransactions>> = acrepository.getAllInfo(21).asLiveData()

    init {
        //uncomment functions below to load related api data
        //getAccounts()
        //getTransactions()
    }

    private fun getAccounts(){
        viewModelScope.launch(Dispatchers.IO) {
            val req = apirepository.getAccounts()
            acrepository.insertMultiple(req)
        }
    }

    private fun getTransactions(){
        viewModelScope.launch(Dispatchers.IO){
            val req = tapirepository.getTransactions()
            trRepository.insertMultiple(req)
        }
    }

    private fun getSpecific(){
        viewModelScope.launch(Dispatchers.IO) {
            val customer = corepo.getAllInfo(21).asLiveData()
        }
    }
}


class CustomerOverviewViewModelFactory(private val application: Application) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CustomerOverviewViewModel::class.java)){
            @Suppress("Unchecked_CAST")
            return CustomerOverviewViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown Viewmodel class")
    }
}