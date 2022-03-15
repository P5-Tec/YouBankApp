package com.example.youbank.models


import androidx.room.Embedded
import androidx.room.Relation

data class AccountWithCards(
    @Embedded val account: Account,
    @Relation(
        parentColumn = "accountId",
        entityColumn = "accountId"
    )
    val cards: List<Card>
)
