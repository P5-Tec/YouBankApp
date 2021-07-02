package com.example.youbank.helpers

import androidx.room.TypeConverter
import com.example.youbank.models.AccountType

class Converters {

    @TypeConverter
    fun fromAccountType(value: AccountType?): Int? {
        return value?.ordinal
    }

    @TypeConverter
    fun toAccountType(value: Int?): AccountType {
        return when (value) {
            0 -> AccountType.Checking
            1 -> AccountType.Holdings
            else -> AccountType.Unknown
        }
    }
}