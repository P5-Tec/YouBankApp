package com.example.youbank.room.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.youbank.models.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDao {

    @Query("SELECT * FROM transaction_table WHERE transactionId = :id ORDER BY transactionId DESC")
    fun getTransactionById(id: Int): Flow<Transaction>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(transaction: Transaction)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMultiple(accounts: List<Transaction>)

    @Query("SELECT * FROM transaction_table ORDER BY transactionId DESC")
    fun getAllTransactions(): Flow<List<Transaction>>

    @Query("SELECT * FROM transaction_table WHERE accountId = :id ORDER BY transactionId DESC")
    fun getTransactionsByAccountId(id: Int): Flow<Transaction>
}