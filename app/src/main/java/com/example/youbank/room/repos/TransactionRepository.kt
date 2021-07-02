package com.example.youbank.room.repos

import androidx.annotation.WorkerThread
import com.example.youbank.models.Transaction
import com.example.youbank.room.daos.TransactionDao
import kotlinx.coroutines.flow.Flow

class TransactionRepository(private val transactionDao: TransactionDao) {

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(transaction: Transaction) {
        transactionDao.insert(transaction)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertMultiple(transaction: List<Transaction>) {
        transaction.let {
            transactionDao.insertMultiple(it)
        }
    }

    @WorkerThread
    fun getAllTransactions(): Flow<List<Transaction>> {
        return transactionDao.getAllTransactions()
    }

}