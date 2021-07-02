package com.example.youbank.room.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.youbank.models.Account
import com.example.youbank.models.AccountWithTransactions
import com.example.youbank.models.Card
import kotlinx.coroutines.flow.Flow

@Dao
interface AccountDao {

    @Query("SELECT * FROM account_table")
    fun getAccounts(): LiveData<Account>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAccount(a: Account)

    //@Insert(onConflict = OnConflictStrategy.IGNORE)
    @Insert()
    suspend fun insertMultiple(accounts: List<Account>)

    @Delete
    fun deleteAccount(a: Account)

    @Transaction
    @Query("SELECT * FROM account_table WHERE customerId = :id")
    fun getAccountWithTransactionsById(id: Int): Flow<List<AccountWithTransactions>>

    @Query("SELECT * FROM account_table")
    fun getAllAccounts(): Flow<List<Account>>
}