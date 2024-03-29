package com.example.youbank.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.youbank.helpers.Converters
import com.example.youbank.models.Account
import com.example.youbank.models.Card
import com.example.youbank.models.Customer
import com.example.youbank.models.Transaction
import com.example.youbank.room.daos.AccountDao
import com.example.youbank.room.daos.CardDao
import com.example.youbank.room.daos.CustomerDao
import com.example.youbank.room.daos.TransactionDao
import kotlinx.coroutines.CoroutineScope

@Database(entities = [Customer::class, Account::class, Card::class, Transaction::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class CustomerDatabase: RoomDatabase() {

    abstract fun customerDao(): CustomerDao
    abstract fun accountDao(): AccountDao
    abstract fun transactionDao(): TransactionDao
    abstract fun cardDao(): CardDao

    companion object {
        @Volatile
        private var INSTANCE: CustomerDatabase? = null

        fun getDatabase(context: Context): CustomerDatabase {
            return INSTANCE
                ?: synchronized(this) {
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        CustomerDatabase::class.java,
                        "customer_database")
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                    return instance
                }
        }

    }
}