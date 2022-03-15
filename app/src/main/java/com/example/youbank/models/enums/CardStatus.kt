package com.example.youbank.models.enums

import com.google.gson.annotations.SerializedName

enum class CardStatus(val value: Int) {
    @SerializedName("0")
    Active(0),
    @SerializedName("1")
    Frozen(1),
    @SerializedName("2")
    Inactive(2),
    Unknown(3)
}