package com.example.youbank.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transaction_table")
data class Transaction(
    @PrimaryKey
    var transactionId: Int? = 0,
    val transactionAmount: Double = 0.0,
    val status: Int? = 0,
    val accountId: Int?
)
