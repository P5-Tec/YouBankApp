package com.example.youbank.room.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.youbank.models.Card
import kotlinx.coroutines.flow.Flow

@Dao
interface CardDao {

    @Query("SELECT * FROM cards_table")
    fun getCards(): LiveData<List<Card>>

    @Query("SELECT * FROM cards_table")
    fun getAllCards(): Flow<List<Card>>

    @Insert
    fun addCards(c: Card)

    @Insert
    //@Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMultiple(cards: List<Card>)

    @Delete
    fun deleteCard(c: Card)


}