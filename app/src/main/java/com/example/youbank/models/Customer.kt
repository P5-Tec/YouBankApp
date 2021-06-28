package com.example.youbank.models

import androidx.room.Entity
import androidx.room.PrimaryKey

/*
@Entity(tableName = "customer_table")
class Customer {
    @PrimaryKey(autoGenerate = false)
    var customerId: Int = 0
    var fullName: String = ""
    var phone: String = ""
    var address: String = ""
    var birthday: String? = null
    var email: String = ""
    var password: String = ""
}
*/

/*
@Entity(tableName = "customer_table")
data class Customer(
    @PrimaryKey
    var customerId: Int = 0
){
    var fullName: String = ""
    var phone: String = ""
    var address: String = ""
    var email: String = ""
    var birthday: String? = null
    var password: String = ""
}
*/

@Entity(tableName = "customer_table")
class Customer{
    @PrimaryKey
    var customerId: Int = 0
    var fullName: String =""
    var phone: String =""
    var address: String=""
    var email: String=""
    var birthday: String? = null
    var password: String = ""
    var pincode: String? = ""
}


