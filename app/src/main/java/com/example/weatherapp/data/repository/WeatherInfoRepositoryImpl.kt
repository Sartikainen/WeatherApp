package com.example.weatherapp.data.repository

import android.content.Context
import android.util.Log
import com.example.weatherapp.data.api.ApiFactory
import com.example.weatherapp.data.database.AppDatabase
import com.example.weatherapp.mapper.toDbModel
import com.example.weatherapp.mapper.toEntity
import com.example.weatherapp.pojo.WeatherInfo
import io.reactivex.Completable
import io.reactivex.Flowable

class WeatherInfoRepositoryImpl(
    context: Context,
) : WeatherInfoRepository {

    private val weatherApi = ApiFactory.apiService
    private val db = AppDatabase.getInstance(context)

    override fun getWeatherInfo(city: String): Flowable<WeatherInfo> {
        return db.weatherInfoDao().getWeatherInfo(city)
            .map {
                it.toEntity()
            }
    }

    override fun fetchWeatherInfo(city: String, days: Int): Completable {
        return try {
            weatherApi.getWeatherInfo(city = city, days = days)
                .map {
                    it.toDbModel()
                }
                .flatMapCompletable {
                    db.weatherInfoDao().insertWeatherInfo(it)
                }
        } catch (e: Exception) {
            Log.e("AppError", "Unexpected error: ${e.message}", e)
        } as Completable
    }
}