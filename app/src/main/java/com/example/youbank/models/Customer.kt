package com.example.youbank.models

class Customer(val name: String) {
    var customerId: Int? = null
    var fullName: String = name
    lateinit var phone: String
    lateinit var address: String
    lateinit  var birthday: String
    lateinit  var email: String
    lateinit var accounts:ArrayList<Account>
}
