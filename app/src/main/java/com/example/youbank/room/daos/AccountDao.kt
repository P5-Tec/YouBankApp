package com.example.youbank.room.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.youbank.room.models.RoomAccount

@Dao
interface AccountDao {

    @Query("SELECT * FROM accounts_table")
    fun getAccounts(): LiveData<List<RoomAccount>>

    @Insert
    fun addAccount(a: RoomAccount)

    @Delete
    fun deleteAccount(a: RoomAccount)
}