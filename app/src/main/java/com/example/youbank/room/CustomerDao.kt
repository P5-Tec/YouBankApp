package com.example.youbank.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CustomerDao {

    @Query("SELECT * FROM RoomCustomer")
    fun getCustomer(): LiveData<RoomCustomer>

    //@Insert(onConflict = OnConflictStrategy.IGNORE)
    @Insert
    fun addCustomer(c: RoomCustomer)

    @Delete
    fun deleteCustomer(c: RoomCustomer)
}