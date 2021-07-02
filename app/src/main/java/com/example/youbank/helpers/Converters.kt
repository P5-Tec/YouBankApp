package com.example.youbank.helpers

import androidx.room.TypeConverter
import com.example.youbank.models.AccountType

//@ProvidedTypeConverter
class Converters {

    //@TypeConverter
    //fun fromAccountType(at: AccountType): Int {
    //    return at.ordinal
    //}
    //
    //@TypeConverter
    //fun toAccountType(at: Int): AccountType {
    //    return AccountType.valueOf(at.toString())
    //}

    //@TypeConverter
    //fun fromAccountType(at: AccountType?) = at?.ordinal
    //
    //@TypeConverter
    //fun toAccountType(at: Int?) = enumValues<AccountType>()

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