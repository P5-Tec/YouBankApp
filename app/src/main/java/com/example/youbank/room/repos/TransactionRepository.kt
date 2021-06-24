package com.example.youbank.room.repos

import androidx.annotation.WorkerThread
import com.example.youbank.room.daos.TransactionDao
import com.example.youbank.models.Transaction

class TransactionRepository(private val transactionDao: TransactionDao) {
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(transaction: Transaction){
        transactionDao.insert(transaction)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertMultiple(transaction: List<Transaction>) {
        transaction?.let {
            transactionDao.insertMultiple(it) }
    }
}