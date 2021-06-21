package com.example.youbank.room.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.youbank.models.Customer

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
}