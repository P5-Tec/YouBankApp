package com.example.youbank.room.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.youbank.room.models.RoomCustomer

@Dao
interface CustomerDao {

    @Query("SELECT * FROM customer_table")
    fun getCustomer(): LiveData<RoomCustomer>

    @Insert
    fun addCustomer(c: RoomCustomer)

    @Delete
    fun deleteCustomer(c: RoomCustomer)
}