package com.example.weatherapp.data.api.model

import com.google.gson.annotations.SerializedName

data class WeatherInfoApiResponse(
    @SerializedName("location")
    val location: LocationApiResponse? = null,
    @SerializedName("current")
    val current: CurrentApiResponse? = null,
    @SerializedName("forecast")
    val forecast: ForecastApiResponse? = null
)