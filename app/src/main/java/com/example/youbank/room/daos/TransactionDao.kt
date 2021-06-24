package com.example.youbank.room.daos

import androidx.room.*
import com.example.youbank.models.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDao {

    @Query("SELECT * FROM transaction_table WHERE transactionId = :id ORDER BY transactionId DESC")
    fun getCustomerById(id: Int) : Flow <Transaction>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(transaction: Transaction)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMultiple(accounts: List<Transaction>)
}