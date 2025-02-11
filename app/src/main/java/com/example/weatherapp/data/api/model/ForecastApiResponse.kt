package com.example.weatherapp.data.api.model

import com.google.gson.annotations.SerializedName

data class ForecastApiResponse(
    @SerializedName("forecastday")
    val forecastDayList: List<ForecastDayApiResponse>? = null
)