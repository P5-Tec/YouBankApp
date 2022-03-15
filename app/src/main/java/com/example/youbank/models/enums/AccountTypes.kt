package com.example.youbank.models.enums

import com.google.gson.annotations.SerializedName

enum class AccountTypes(val value: Int) {
    @SerializedName("0")
    Checking(0),
    @SerializedName("1")
    Holdings(1),
    Unknown(2)
}