package com.example.weatherapp.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class WeatherInfo (
    @SerializedName("location")
    @Expose
    val location: Location,
    @SerializedName("current")
    @Expose
    val current: Current? = null,
    @SerializedName("forecast")
    @Expose
    val forecast: Forecast? = null
)