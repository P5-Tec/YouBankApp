package com.example.youbank.helpers

import androidx.room.TypeConverter
import com.example.youbank.models.Account
import com.example.youbank.models.AccountType

class Converters {

    @TypeConverter
    fun fromAccountType(at: AccountType): Int {
        return at.ordinal
    }

    @TypeConverter
    fun toAccountType(at: Int): AccountType {
        return AccountType.valueOf(at.toString())
    }

    //@TypeConverter
    //fun fromAccountType(at: AccountType) = at.ordinal
    //
    //@TypeConverter
    //fun toAccountType(at: Int) = enumValues<AccountType>()[at]
}