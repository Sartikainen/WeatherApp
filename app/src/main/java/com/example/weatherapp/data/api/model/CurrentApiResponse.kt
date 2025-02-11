package com.example.weatherapp.data.api.model

import com.google.gson.annotations.SerializedName

data class CurrentApiResponse(
    @SerializedName("temp_c")
    val tempC: Double,
    @SerializedName("condition")
    val condition: ConditionApiResponse? = null,
    @SerializedName("wind_kph")
    val windKph: Double? = null,
    @SerializedName("pressure_mb")
    val pressureMb: Double? = null,
    @SerializedName("humidity")
    val humidity: Int? = null
)