package com.example.youbank.models

import com.google.gson.annotations.SerializedName

enum class AccountType(val value: Int) {
    @SerializedName("0")
    Checking(0),
    @SerializedName("1")
    Holdings(1),
    Unknown(2)
}