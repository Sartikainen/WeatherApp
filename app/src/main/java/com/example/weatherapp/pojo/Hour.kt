package com.example.weatherapp.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Hour (
    @SerializedName("time")
    @Expose
    val time: String? = null,
    @SerializedName("temp_c")
    @Expose
    val tempC: Double? = null
)