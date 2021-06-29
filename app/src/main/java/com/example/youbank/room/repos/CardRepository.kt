package com.example.youbank.room.repos

import android.util.Log
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.youbank.models.Account
import com.example.youbank.models.Card
import com.example.youbank.room.daos.CardDao

class CardRepository(private val cardDao: CardDao) {

    val readCards: LiveData<List<Card>> = cardDao.getCards()

    fun addCards(c: Card) {
        cardDao.addCards(c)
    }

    //@Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertMultiple(cards: List<Card>) {
        cards?.let {
            Log.i("card", it[0].cardId.toString())
            cardDao.insertMultiple(it) }
    }
}