package com.example.youbank.models

class Customer {
    var customerId: Int = -1
    var fullName = String()
    var birthday = String()
    var email = String()
    var phone = String()
    var address = String()
    var password = String()
    lateinit var accounts: ArrayList<Account>
}