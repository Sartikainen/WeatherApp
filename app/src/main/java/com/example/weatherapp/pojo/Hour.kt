package com.example.weatherapp.pojo

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.weatherapp.converters.ConverterCondition
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "hour_values")
@TypeConverters(value = [ConverterCondition::class])
data class Hour (
    @PrimaryKey
    @SerializedName("time")
    @Expose
    val time: String,
    @SerializedName("temp_c")
    @Expose
    val tempC: Double? = null
)