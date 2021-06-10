package com.example.youbank.room.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.youbank.models.AccountType

@Entity(tableName = "accounts_table")
data class RoomAccount(
    @PrimaryKey(autoGenerate = true)
    val AID: Int,
    val accountId: Int,
    val accountNumber: String,
    val accountType: AccountType,
    val balance: Double
)