package com.example.youbank.models

class Customer {
    var customerId: Int = -1
    lateinit var fullName: String
    lateinit var birthday: String
    lateinit var email: String
    lateinit var phone: String
    lateinit var address: String
    lateinit var password: String
    lateinit var accounts: ArrayList<Account>
}