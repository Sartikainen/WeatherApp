package com.example.weatherapp.data.api.model

import com.google.gson.annotations.SerializedName

data class ConditionHourApiResponse(
    @SerializedName("text")
    var text: String? = null,
    @SerializedName("icon")
    var icon: String? = null,
    @SerializedName("code")
    var code: Int? = null
)