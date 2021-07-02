package com.example.youbank.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

// TODO - Use below enums
enum class CardType { Debit, Credit, Virtual }
enum class CardStatus { Active, Frosen, Inactive }

@Entity(tableName = "cards_table")
class Card(
    @PrimaryKey
    var cardId: Long = -1
) {
    @SerializedName("cardnNumber")
    var cardNumber: Long = -1
    var ccv: Int = -1
    var accountId: Int = -1
    lateinit var expirationDate: String
    //lateinit var cardType: CardType
    //lateinit var cardStatus: CardStatus
}

