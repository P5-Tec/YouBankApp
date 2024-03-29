package com.example.youbank.models

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "customer_table")
class Customer {
    @PrimaryKey
    var customerId: Int = 0
    var fullName: String = ""
    var phone: String = ""
    var address: String = ""
    var email: String = ""
    var birthday: String? = null
    var password: String = ""
    var pincode: String = ""

    @Ignore
    lateinit var accounts: List<Account>
}


