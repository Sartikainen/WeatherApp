package com.example.weatherapp.data.api.model

import com.google.gson.annotations.SerializedName

data class LocationApiResponse(
    @SerializedName("name")
    val name: String,
    @SerializedName("country")
    val country: String? = null,
    @SerializedName("localtime")
    val localtime: String? = null
)