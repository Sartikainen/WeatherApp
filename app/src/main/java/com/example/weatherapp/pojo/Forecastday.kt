package com.example.weatherapp.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Forecastday (
    @SerializedName("date")
    @Expose
    val date: String? = null,
    @SerializedName("hour")
    @Expose
    val hour: List<Hour>? = null
)