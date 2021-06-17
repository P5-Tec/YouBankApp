package com.example.youbank.models

import android.util.Log
import androidx.room.Entity
import androidx.room.PrimaryKey

enum class AccountType { Basic, Premium }

@Entity(tableName = "accounts_table")
class Account {
    @PrimaryKey(autoGenerate = false)
    var accountId: Int = -1
    var accountNumber: String = generateAccNumber()
    lateinit var accountType: AccountType
    var balance: Double = 0.0
    //lateinit var cards: List<Card>
}

fun generateAccNumber(): String {
    val acclength = 16
    val chunksize = 4
    val numbSet = (0..9)
    val result = (1..acclength).map { numbSet.random() }.joinToString("").chunked(chunksize).joinToString(" ")
    Log.i("numb", result)
    return result
}