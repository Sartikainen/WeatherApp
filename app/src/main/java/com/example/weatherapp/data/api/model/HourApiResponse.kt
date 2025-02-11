package com.example.weatherapp.data.api.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class HourApiResponse(
    @SerializedName("time")
    val time: String,
    @SerializedName("temp_c")
    val tempC: Double? = null,
    @SerializedName("condition")
    @Expose
    val condition: ConditionHourApiResponse? = null
)