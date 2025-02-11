package com.example.weatherapp.mapper

import com.example.weatherapp.data.api.model.WeatherInfoApiResponse
import com.example.weatherapp.data.database.model.WeatherInfoDbModel
import com.example.weatherapp.pojo.Current
import com.example.weatherapp.pojo.Forecast
import com.example.weatherapp.pojo.Location
import com.example.weatherapp.pojo.WeatherInfo
import com.example.weatherapp.utils.EMPTY
import com.google.gson.Gson

// Extension function
fun WeatherInfoApiResponse.toDbModel(): WeatherInfoDbModel {
    val gson = Gson()
    return WeatherInfoDbModel(
        cityName = location?.name ?: String.EMPTY,
        location = gson.toJson(location),
        current = gson.toJson(current),
        forecast = gson.toJson(forecast)
    )
}

fun WeatherInfoDbModel.toEntity(): WeatherInfo {
    val gson = Gson()
    return WeatherInfo(
        location = location?.let { gson.fromJson(it, Location::class.java) },
        current = current?.let { gson.fromJson(it, Current::class.java) },
        forecast = forecast?.let { gson.fromJson(it, Forecast::class.java) }
    )
}
