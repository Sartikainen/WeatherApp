package com.example.weatherapp.pojo

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "current_values")
data class Current (
    @PrimaryKey
    @SerializedName("temp_c")
    @Expose
    val tempC: Double,
//    @SerializedName("condition")
//    @Expose
//    val condition: Condition? = null,
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