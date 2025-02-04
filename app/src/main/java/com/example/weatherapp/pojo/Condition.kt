package com.example.weatherapp.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Condition(
    @SerializedName("text")
    @Expose
    val text: String? = null,
    @SerializedName("icon")
    @Expose
    val icon: String? = null,
    @SerializedName("code")
    @Expose
    val code: Int? = null
)