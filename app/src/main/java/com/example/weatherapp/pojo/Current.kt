package com.example.weatherapp.pojo

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.weatherapp.api.ApiFactory.BASE_IMAGE_URL
import com.example.weatherapp.converters.ConverterCondition
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "current_values")
@TypeConverters(value = [ConverterCondition::class])
data class Current (
    @PrimaryKey
    @SerializedName("temp_c")
    @Expose
    val tempC: Double,
    @SerializedName("condition")
    @Expose
    val condition: Condition? = null,
    @SerializedName("wind_kph")
    @Expose
    val windKph: Double? = null,
    @SerializedName("pressure_mb")
    @Expose
    val pressureMb: Double? = null,
    @SerializedName("humidity")
    @Expose
    val humidity: Int? = null
) {
    fun getFullImageURL(): String {
        return "$BASE_IMAGE_URL$condition.icon"
    }
}