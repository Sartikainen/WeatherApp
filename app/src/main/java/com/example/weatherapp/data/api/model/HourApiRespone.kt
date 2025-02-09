package com.example.weatherapp.data.api.model

import com.google.gson.annotations.SerializedName

data class HourApiRespone (
    @SerializedName("time")
    val time: String,
    @SerializedName("temp_c")
    val tempC: Double? = null
)