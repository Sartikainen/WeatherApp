package com.example.weatherapp.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Forecast (
    @SerializedName("forecastday")
    @Expose
    val forecastday: List<Forecastday>? = null
)