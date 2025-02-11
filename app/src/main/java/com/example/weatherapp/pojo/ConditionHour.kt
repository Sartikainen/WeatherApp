package com.example.weatherapp.pojo

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "condition_hour_values")
data class ConditionHour (
    @SerializedName("text")
    @Expose
    var text: String? = null,
    @SerializedName("icon")
    @Expose
    var icon: String? = null,
    @PrimaryKey
    @SerializedName("code")
    @Expose
    var code: Int? = null
)