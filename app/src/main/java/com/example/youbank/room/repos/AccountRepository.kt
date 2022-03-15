package com.example.youbank.room.repos

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.youbank.models.Account
import com.example.youbank.models.AccountWithTransactions
import com.example.youbank.room.daos.AccountDao
import kotlinx.coroutines.flow.Flow

class AccountRepository(private val accountDao: AccountDao) {

    val readAccounts: LiveData<Account> = accountDao.getAccounts()

    fun insertAccount(a: Account) {
        accountDao.insertAccount(a)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertMultiple(accounts: List<Account>) {
        accounts.let {
            accountDao.insertMultiple(it)
        }
    }

    @WorkerThread
    fun getAllInfo(id: Int): Flow<List<AccountWithTransactions>> {
        return accountDao.getAccountWithTransactionsById(id)
    }

    fun getallAccounts(): Flow<List<Account>> {
        return accountDao.getAllAccounts()
    }

    fun getAccountById(aId: Int): Flow<List<AccountWithTransactions>> {
        return accountDao.getAccountWithTransactionsById(aId)
    }
}