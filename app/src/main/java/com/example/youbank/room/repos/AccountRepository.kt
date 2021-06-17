package com.example.youbank.room.repos

import androidx.lifecycle.LiveData
import com.example.youbank.models.Account
import com.example.youbank.room.daos.AccountDao

class AccountRepository(private val accountDao: AccountDao) {

    val readAccounts: LiveData<Account> = accountDao.getAccounts()

    fun addAccounts(a: Account) {
        accountDao.addAccount(a)
    }
}