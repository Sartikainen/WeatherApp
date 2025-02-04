package com.example.weatherapp.pojo

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.weatherapp.converters.Converter
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "forecast_values")
@TypeConverters(value = [Converter::class])
data class Forecastday (
    @PrimaryKey
    @SerializedName("date")
    @Expose
    val date: String,
    @SerializedName("hour")
    @Expose
    val hour: List<Hour>? = null
)