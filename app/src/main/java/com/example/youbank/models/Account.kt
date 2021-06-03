package com.example.youbank.models

import android.util.Log

enum class AccountType { Basic, Premium }

class Account {
    var accountId: Int? = null
    var accountNumber: String? = generateAccNumber()
    var accountBalance: Int? = null
    lateinit var accountType: AccountType
}

fun generateAccNumber(): String {
    val acclength = 16
    val chunksize = 4
    val numbSet = (0..9)
    val result = (1..acclength).map { numbSet.random() }.joinToString("").chunked(chunksize).joinToString(" ")
    Log.i("numb", result)
    return result
}