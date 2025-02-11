package com.example.weatherapp.domain

import com.example.weatherapp.data.repository.WeatherInfoRepository

class FetchWeatherInfoUseCase(
    private val weatherRepository: WeatherInfoRepository
) {
    fun execute(city: String, days: Int) = weatherRepository.fetchWeatherInfo(city, days)
}