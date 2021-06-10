package com.example.youbank.room.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.youbank.models.AccountType
import com.example.youbank.models.CardStatus
import com.example.youbank.models.CardType

@Entity(tableName = "cards_table")
data class RoomCard(
    @PrimaryKey(autoGenerate = true)
    val CID: Int,
    val cardId: Int,
    val cardNumber: Int,
    val ccv: Int,
    val expirationDate: String,
    val cardType: CardType,
    val cardStatus: CardStatus
)