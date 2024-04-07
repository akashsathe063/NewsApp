package com.example.newsapp.db

import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.newsapp.model.Source
import com.google.gson.Gson

@TypeConverters
class TypeConverter {
    @TypeConverter
    fun fromSourceToString(attribute: Source?): String {
        return Gson().toJson(attribute)
    }
    @TypeConverter
    fun fromStringToSource(attribute: String?): Source {
        return Gson().fromJson(attribute, Source::class.java)
    }
}