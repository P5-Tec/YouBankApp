package com.example.youbank.room.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.youbank.models.Card

@Dao
interface CardDao {

    @Query("SELECT * FROM cards_table")
    fun getCards(): LiveData<List<Card>>

    @Insert
    fun addCards(c: Card)

    @Delete
    fun deleteCard(c: Card)
}