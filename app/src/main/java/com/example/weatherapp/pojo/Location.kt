package com.example.weatherapp.pojo

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "location_values")
data class Location (
    @PrimaryKey
    @SerializedName("name")
    @Expose
    val name: String,
    @SerializedName("country")
    @Expose
    val country:String? = null,
    @SerializedName("localtime")
    @Expose
    val localtime: String? = null
)