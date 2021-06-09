package com.example.youbank.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Customer(
    @PrimaryKey
    val customerId: Int,
    val fullName: String,
    val birthday: String,
    val email: String,
    val phone: String,
    val address: String
)