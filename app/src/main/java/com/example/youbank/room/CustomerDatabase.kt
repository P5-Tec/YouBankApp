package com.example.youbank.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.youbank.room.daos.AccountDao
import com.example.youbank.room.daos.CardDao
import com.example.youbank.room.daos.CustomerDao
import com.example.youbank.room.models.RoomAccount
import com.example.youbank.room.models.RoomCard
import com.example.youbank.room.models.RoomCustomer

@Database(entities = [RoomCustomer::class, RoomAccount::class, RoomCard::class], version = 1, exportSchema = false)
abstract class CustomerDatabase: RoomDatabase() {

    abstract fun customerDao(): CustomerDao
    abstract fun accountDao(): AccountDao
    abstract fun cardDao(): CardDao

    companion object {
        @Volatile
        private var INSTANCE: CustomerDatabase? = null

        fun getDatabase(context: Context): CustomerDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CustomerDatabase::class.java,
                    "customer_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}