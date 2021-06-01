package com.example.youbank.models

class Customer {
    var customerId: Int? = null
    var cpr: String = ""
    var fullName: String = ""
    var email: String = ""
    var phone: String = ""
    var address: String = ""
    var password: String = ""
    lateinit var accounts: ArrayList<Account>
}






