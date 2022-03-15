package com.example.youbank.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.youbank.models.enums.CardStatus
import com.example.youbank.models.enums.CardTypes
import com.google.gson.annotations.SerializedName

@Entity(tableName = "cards_table")
class Card {
    @PrimaryKey
    var cardId: Long = -1
    var cardNumber: Long = -1
    var ccv: Int = -1
    var expirationDate: String = ""
    var cardType: CardTypes = CardTypes.Unknown
    var cardStatus: CardStatus = CardStatus.Unknown
    var accountId: Int = -1
}

