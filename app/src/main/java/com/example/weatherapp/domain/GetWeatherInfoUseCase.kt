package com.example.weatherapp.domain

import com.example.weatherapp.data.repository.WeatherInfoRepository

class GetWeatherInfoUseCase(
    private val weatherRepository: WeatherInfoRepository
) {
     fun execute(city: String) = weatherRepository.getWeatherInfo(city)
}