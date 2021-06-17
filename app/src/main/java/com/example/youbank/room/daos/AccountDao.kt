package com.example.youbank.room.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface AccountDao {

    @Query("SELECT * FROM accounts_table")
    fun getAccounts(): LiveData<com.example.youbank.models.Account>

    @Insert
    fun addAccount(a: com.example.youbank.models.Account)

    @Delete
    fun deleteAccount(a: com.example.youbank.models.Account)
}