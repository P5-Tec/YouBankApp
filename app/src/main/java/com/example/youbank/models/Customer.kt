package com.example.youbank.models

import java.time.LocalDate
import java.util.*
import kotlin.collections.ArrayList

class Customer {
    var customerId: Int? = null
    lateinit var fullName: String
    lateinit var birthday: String
    lateinit var email: String
    lateinit var phone: String
    lateinit var address: String
    lateinit var password: String
    lateinit var accounts: ArrayList<Account>
}






