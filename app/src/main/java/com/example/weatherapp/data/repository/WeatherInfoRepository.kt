package com.example.weatherapp.data.repository

import com.example.weatherapp.pojo.WeatherInfo
import io.reactivex.Completable
import io.reactivex.Flowable

interface WeatherInfoRepository {

    fun getWeatherInfo(city: String): Flowable<WeatherInfo>

    fun fetchWeatherInfo(city: String, days: Int): Completable
}