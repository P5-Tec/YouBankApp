package com.example.youbank.room.repos

import androidx.lifecycle.LiveData
import com.example.youbank.room.daos.AccountDao
import com.example.youbank.room.daos.CustomerDao
import com.example.youbank.room.models.RoomAccount
import com.example.youbank.room.models.RoomCustomer

class AccountRepository (private val accountDao: AccountDao) {

    val readAccounts: LiveData<List<RoomAccount>> = accountDao.getAccounts()

    fun addAccounts(a: RoomAccount) {
        accountDao.addAccount(a)
    }
}