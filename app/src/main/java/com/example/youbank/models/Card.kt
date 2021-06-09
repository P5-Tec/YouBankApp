package com.example.youbank.models

enum class CardType { Debit, Credit, Virtual }
enum class CardStatus { Active, Frosen, Inactive }

class Card {
    var cardId: Int = -1
    var cardNumber: Int = -1
    var ccv: Int = -1
    lateinit var expirationDate: String
    lateinit var cardType: CardType
    lateinit var cardStatus: CardStatus
}