package com.example.youbank.room.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.youbank.models.Customer
import com.example.youbank.models.CustomerWithAccounts
import kotlinx.coroutines.flow.Flow

@Dao
interface CustomerDao {

    @Query("SELECT * FROM customer_table")
    fun getCustomer(): LiveData<Customer>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertCustomer(c: Customer)

    @Update
    suspend fun updateCustomer(c: Customer)

    @Delete
    suspend fun deleteCustomer(c: Customer)

    @Transaction
    @Query("SELECT * FROM customer_table WHERE customerId = :id")
    fun getCustomerWithAccountsID(id: Int): Flow<List<CustomerWithAccounts>>
}