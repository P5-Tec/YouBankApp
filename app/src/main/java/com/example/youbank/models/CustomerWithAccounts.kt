package com.example.youbank.models

import androidx.room.Embedded
import androidx.room.Relation

data class CustomerWithAccounts(
    @Embedded val customer: Customer,
    @Relation(
        parentColumn = "customerId",
        entityColumn = "customerId"
    )
    val accounts: List<Account>
)
