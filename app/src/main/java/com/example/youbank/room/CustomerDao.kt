package com.example.youbank.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CustomerDao {

    @Query("SELECT * FROM Customer")
    fun getCustomer(): Customer

    //@Insert(onConflict = OnConflictStrategy.IGNORE)
    @Insert
    fun addCustomer(c: Customer)

    @Delete
    fun deleteCustomer(c: Customer)
}