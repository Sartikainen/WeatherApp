package com.example.weatherapp.data.api.model

import com.google.gson.annotations.SerializedName

data class ForecastDayApiResponse(
    @SerializedName("date")
    val date: String,
    @SerializedName("hour")
    val hour: List<HourApiResponse>? = null
)