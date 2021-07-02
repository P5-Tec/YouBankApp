package com.example.youbank.models

import android.util.Log
import androidx.room.*

enum class AccountType(value: Int) { Checking(0), Holdings(1) }

@Entity(
    tableName = "account_table"
    //,foreignKeys = [ForeignKey(entity  = Transaction::class, parentColumns = ["accountId"], childColumns = ["accountId"])], indices = [Index("accountId")]
)
data class Account(
    @PrimaryKey
    var accountId: Int = -1
){
    var accountNumber: Long? = 0
    var accountType: AccountType? = AccountType.Checking
    var balance: Double = 0.0
    var customerId: Int = 0
    @Ignore
    var cards: List<Card> = listOf()
    @Ignore
    var transactions: List<Transaction> = listOf()

}

fun generateAccNumber(): String {
    val acclength = 16
    val chunksize = 4
    val numbSet = (0..9)
    val result = (1..acclength).map { numbSet.random() }.joinToString("").chunked(chunksize).joinToString(" ")
    Log.i("numb", result)
    return result
}