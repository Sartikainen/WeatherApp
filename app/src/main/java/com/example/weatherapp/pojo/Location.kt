package com.example.weatherapp.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Location (
    @SerializedName("name")
    @Expose
    val name: String? = null,
    @SerializedName("country")
    @Expose
    val country:String? = null,
    @SerializedName("localtime")
    @Expose
    val localtime: String? = null
)