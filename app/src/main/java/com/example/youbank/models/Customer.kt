package com.example.youbank.models

class Customer {
    var customerId: Int? = null
    lateinit var fullName: String
    lateinit var birthday: String
    var email: String? = null
    lateinit var phone: String
    lateinit var address: String
    var password: String? = null
    var accounts: ArrayList<Account>? = null
}