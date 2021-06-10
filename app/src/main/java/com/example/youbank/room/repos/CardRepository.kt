package com.example.youbank.room.repos

import androidx.lifecycle.LiveData
import com.example.youbank.room.daos.CardDao
import com.example.youbank.room.models.RoomCard

class CardRepository(private val cardDao: CardDao) {

    val readCards: LiveData<List<RoomCard>> = cardDao.getCards()

    fun addCards(c: RoomCard) {
        cardDao.addCards(c)
    }
}