package com.example.weatherapp.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Current (
    @SerializedName("temp_c")
    @Expose
    val tempC: Double? = null,
    @SerializedName("condition")
    @Expose
    val condition: Condition? = null,
    @SerializedName("wind_kph")
    @Expose
    val windKph: Double? = null,
    @SerializedName("pressure_mb")
    @Expose
    val pressureMb: Double? = null,
    @SerializedName("humidity")
    @Expose
    val humidity: Int? = null
)