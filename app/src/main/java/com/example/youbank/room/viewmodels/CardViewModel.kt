package com.example.youbank.room.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.youbank.models.Card
import com.example.youbank.room.CustomerDatabase
import com.example.youbank.room.repos.CardRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CardViewModel(application: Application): AndroidViewModel(application) {

    val readCards: LiveData<List<Card>>
    private val repository: CardRepository

    init {
        val cardDao = CustomerDatabase.getDatabase(application, viewModelScope).cardDao()
        repository = CardRepository(cardDao)
        readCards = repository.readCards
    }

    fun addCard(c: Card) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addCards(c)
        }
    }

}