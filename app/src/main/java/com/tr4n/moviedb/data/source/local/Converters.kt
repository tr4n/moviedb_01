package com.tr4n.moviedb.data.source.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.tr4n.moviedb.data.model.Genre

class Converters {

    @TypeConverter
    fun listToJsonInt(value: List<Int>?): String = Gson().toJson(value)

    @TypeConverter
    fun jsonStringToListInt(value: String) = Gson().fromJson(value, Array<Int>::class.java).toList()

    @TypeConverter
    fun listToJsonGenre(value: List<Genre>?): String = Gson().toJson(value)

    @TypeConverter
    fun jsonStringToListGenre(value: String) = Gson().fromJson(value, Array<Genre>::class.java).toList()

}
