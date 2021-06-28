package com.example.youbank.models

import androidx.room.Entity
import androidx.room.PrimaryKey

enum class CardType { Debit, Credit, Virtual }
enum class CardStatus { Active, Frosen, Inactive }

@Entity(tableName = "cards_table")
class Card {
    @PrimaryKey(autoGenerate = false)
    var cardId: Int = -1
    var cardNumber: Int = -1
    var ccv: Int = -1
    var accountId: Int = -1
    lateinit var expirationDate: String
    lateinit var cardType: CardType
    lateinit var cardStatus: CardStatus
}

