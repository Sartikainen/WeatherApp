package com.example.weatherapp.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weather_info")
data class WeatherInfoDbModel(
    @PrimaryKey
    val cityName: String,
    val location: String?,
    val current: String?,
    val forecast: String?
)
