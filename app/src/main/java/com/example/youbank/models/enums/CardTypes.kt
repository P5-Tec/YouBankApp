package com.example.youbank.models.enums

import com.google.gson.annotations.SerializedName

enum class CardTypes(val value: Int) {
    @SerializedName("0")
    Debit(0),
    @SerializedName("1")
    Credit(1),
    @SerializedName("2")
    Virtual(2),
    Unknown(3)
}