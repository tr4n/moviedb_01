package com.tr4n.moviedb.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "genre_table")
data class Genre(
    @PrimaryKey
    @SerializedName("id")
    val id : Int,
    @SerializedName("name")
    val name : String
)
