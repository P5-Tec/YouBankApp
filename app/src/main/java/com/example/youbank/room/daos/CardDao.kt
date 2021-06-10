package com.example.youbank.room.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.youbank.models.Card
import com.example.youbank.room.models.RoomCard

@Dao
interface CardDao {

    @Query("SELECT * FROM cards_table")
    fun getCards(): LiveData<List<RoomCard>>

    @Insert
    fun addCards(c: RoomCard)

    @Delete
    fun deleteCard(c: RoomCard)
}