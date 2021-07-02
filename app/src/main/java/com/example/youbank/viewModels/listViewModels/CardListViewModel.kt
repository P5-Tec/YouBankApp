package com.example.youbank.viewModels.listViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.youbank.models.Card
import com.example.youbank.room.CustomerDatabase
import com.example.youbank.room.daos.CardDao
import com.example.youbank.room.repos.CardRepository

class CardListViewModel(application: Application): AndroidViewModel(application) {

    private val cardDao: CardDao = CustomerDatabase.getDatabase(application, viewModelScope).cardDao()
    val repository = CardRepository(cardDao)
    val allCards: LiveData<List<Card>> = repository.getAllCards().asLiveData()
}