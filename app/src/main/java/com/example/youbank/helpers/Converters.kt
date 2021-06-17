package com.example.youbank.helpers

import androidx.room.TypeConverter
import com.example.youbank.models.Account
import com.example.youbank.models.Card
import com.google.gson.Gson
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset


class Converters {

    //@TypeConverter fun toDate(dateString: String?): LocalDateTime? {
    //    return if (dateString == null) {
    //        null
    //    }
    //    else {
    //        LocalDateTime.parse(dateString)
    //    }
    //}
    //
    //@TypeConverter fun toDateString(date: LocalDateTime?): String? {
    //    return date?.toString()
    //}

    //@TypeConverter fun toDate(dateString: String?): LocalDateTime? {
    //    return if (dateString == null) {
    //        null
    //    }
    //    else {
    //        LocalDateTime.parse(dateString)
    //    }
    //}
    //
    //@TypeConverter fun toDateString(date: LocalDateTime?): String? {
    //    return date?.toString()
    //}


    //@TypeConverter
    //fun fromTimestamp(value: Long?): LocalDateTime? {
    //    return value?.let { LocalDateTime.ofInstant(Instant.ofEpochMilli(value), ZoneOffset.UTC) }
    //}
    //
    //@TypeConverter
    //fun dateToTimestamp(date: LocalDateTime?): Long? {
    //    return date?.atZone(ZoneOffset.UTC)?.toInstant()?.toEpochMilli()
    //}

    //
    //
    //@TypeConverter
    //fun fromList(value: List<Account>) = Json.encodeToString(value)
    //
    //@TypeConverter
    //fun toList(value: String) = Json.decodeFromString<List<Account>>(value)



    //@TypeConverter
    //fun listToJson(value: List<String>?) = Gson().toJson(value)
    //
    //@TypeConverter
    //fun jsonToList(value: String) = Gson().fromJson(value, Array<String>::class.java).toList()

}