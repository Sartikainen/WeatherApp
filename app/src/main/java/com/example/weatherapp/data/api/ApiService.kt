package com.example.weatherapp.data.api

import com.example.weatherapp.data.api.model.WeatherInfoApiResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("forecast.json")
    fun getWeatherInfo(
        @Query(QUERY_PARAM_API_KEY) apiKey: String = API_KEY,
        @Query(QUERY_PARAM_CITY) city: String = "Tbilisi",
        @Query(QUERY_PARAM_DAYS) days: Int = 1
    ): Single<WeatherInfoApiResponse>

    companion object {
        private const val QUERY_PARAM_API_KEY = "key"
        private const val QUERY_PARAM_CITY = "q"
        private const val QUERY_PARAM_DAYS = "days"

        private const val API_KEY = "0c8e897e603641b6b48101023250302"
    }
}