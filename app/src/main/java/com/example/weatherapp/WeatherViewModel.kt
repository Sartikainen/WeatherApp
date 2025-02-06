package com.example.weatherapp

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.example.weatherapp.api.ApiFactory
import com.example.weatherapp.database.AppDatabase
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class WeatherViewModel(application: Application) : AndroidViewModel(application) {

    private val db = AppDatabase.getInstance(application)
    private val compositeDisposable = CompositeDisposable()

    private var city: String = "Tbilisi"
    private var days: Int = 3
    private var date : String = "2025-02-06"

    init {
        loadData()
    }

    val location = db.weatherInfoDao().getInfoAboutCity(city)
    val currentWeather = db.weatherInfoDao().getCurrentWeather()
    val infoAboutIcon = db.weatherInfoDao().getInfoAboutIcon()
    val tempPerHour = db.weatherInfoDao().getTempFromHour()
    val forecastWeatherPerHour = db.weatherInfoDao().getForecastWeatherPerHour(date)


    private fun loadData() {
        val disposable = ApiFactory.apiService.getWeatherInfo(city = city, days = days)
            .delaySubscription(10, java.util.concurrent.TimeUnit.SECONDS)
            .repeat()
            .retry()
            .subscribeOn(Schedulers.io())
            .subscribe({
                it?.let { it.current?.let { it.condition?.let { it -> db.weatherInfoDao().insertCondition(it) } } }
                it?.let { it.current?.let { it -> db.weatherInfoDao().insertCurrentWeather(it) } }
                it?.let { it.location?.let { it -> db.weatherInfoDao().insertLocation(it) } }
                it?.let { it.forecast?.let { it.forecastday?.let { it.let { it -> db.weatherInfoDao().insertForecastdayWeather(it) } } } }
                it?.let { it.forecast?.let { it.forecastday?.let { it.map { it.hour?.let { it -> db.weatherInfoDao().insertHourWeather(it) } } } } }
                Log.d("TEST_OF_LOADING_DATA", it.toString())
            }, {
                Log.d("TEST_OF_LOADING_DATA", it.message.toString())
            })
        compositeDisposable.add(disposable)
    }


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}