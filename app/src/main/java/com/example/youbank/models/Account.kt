package com.example.youbank.models

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "account_table")
class Account {
    @PrimaryKey
    var accountId: Int = -1
    var accountNumber: Long = 0
    var accountType: AccountType? = AccountType.Unknown
    var balance: Double = 0.0
    var customerId: Int = 0
    @Ignore
    var cards: List<Card> = listOf()
    @Ignore
    var transactions: List<Transaction> = listOf()
}

//fun generateAccNumber(): String {
//    val acclength = 16
//    val chunksize = 4
//    val numbSet = (0..9)
//    val result = (1..acclength).map { numbSet.random() }.joinToString("").chunked(chunksize).joinToString(" ")
//    Log.i("numb", result)
//    return result
//}