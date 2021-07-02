package com.example.youbank.room.repos

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.youbank.models.Card
import com.example.youbank.room.daos.CardDao
import kotlinx.coroutines.flow.Flow


class CardRepository(private val cardDao: CardDao) {

    val readCards: LiveData<List<Card>> = cardDao.getCards()

    fun addCards(c: Card) {
        cardDao.addCards(c)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertMultiple(cards: List<Card>) {
        cards.let { cardDao.insertMultiple(it) }
    }

    @WorkerThread
    suspend fun insertMultiple2(cards: MutableList<Card>) {
        cards.let { cardDao.insertMultiple(it) }
    }

    @WorkerThread
    fun getAllCards(): Flow<List<Card>> {
        return cardDao.getAllCards()
    }
}