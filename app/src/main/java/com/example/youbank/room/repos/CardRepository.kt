package com.example.youbank.room.repos

import androidx.lifecycle.LiveData
import com.example.youbank.models.Card
import com.example.youbank.room.daos.CardDao

class CardRepository(private val cardDao: CardDao) {

    val readCards: LiveData<List<Card>> = cardDao.getCards()

    fun addCards(c: Card) {
        cardDao.addCards(c)
    }
}