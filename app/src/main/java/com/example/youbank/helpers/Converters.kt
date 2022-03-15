package com.example.youbank.helpers

import androidx.room.TypeConverter
import com.example.youbank.models.enums.AccountTypes
import com.example.youbank.models.enums.CardStatus
import com.example.youbank.models.enums.CardTypes

class Converters {

    @TypeConverter
    fun fromAccountTypes(value: AccountTypes?): Int? {
        return value?.ordinal
    }

    @TypeConverter
    fun toAccountTypes(value: Int?): AccountTypes {
        return when (value) {
            0 -> AccountTypes.Checking
            1 -> AccountTypes.Holdings
            else -> AccountTypes.Unknown
        }
    }

    @TypeConverter
    fun fromCardTypes(value: CardTypes?): Int? {
        return value?.ordinal
    }

    @TypeConverter
    fun toCardTypes(value: Int?): CardTypes {
        return when (value) {
            0 -> CardTypes.Debit
            1 -> CardTypes.Credit
            2 -> CardTypes.Virtual
            else -> CardTypes.Unknown
        }
    }

    @TypeConverter
    fun fromCardStatus(value: CardStatus?): Int? {
        return value?.ordinal
    }

    @TypeConverter
    fun toCardStatus(value: Int?): CardStatus {
        return when (value) {
            0 -> CardStatus.Active
            1 -> CardStatus.Frozen
            2 -> CardStatus.Inactive
            else -> CardStatus.Unknown
        }
    }
}