package com.example.weatherapp

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.weatherapp.api.ApiFactory
import com.example.weatherapp.database.AppDatabase
import com.example.weatherapp.pojo.Hour
import com.example.weatherapp.pojo.Location
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class WeatherViewModel(application: Application) : AndroidViewModel(application) {

    private val db = AppDatabase.getInstance(application)
    private val compositeDisposable = CompositeDisposable()

    private var city: String = "Tbilisi"
    private var days: Int = 1
    private var time: String = "12:00"

    val location = db.weatherInfoDao().getInfoAboutCity(city)
    val currentWeather = db.weatherInfoDao().getCurrentWeather()
    val forecastWeatherPerHour = db.weatherInfoDao().getForecastWeatherPerHour()
    val tempFromHour = db.weatherInfoDao().getTempFromHour(time)

    fun loadData() {
        val disposable = ApiFactory.apiService.getWeatherInfo(city = city, days = days)
            .subscribeOn(Schedulers.io())
            .subscribe({
                it?.let { it.current?.let { it -> db.weatherInfoDao().insertCurrentWeather(it) } }
                it?.let { it.location?.let { it -> db.weatherInfoDao().insertLocation(it) } }
                it?.let { it.forecast?.let { it.forecastday?.let { it -> db.weatherInfoDao().insertForecastdayWeather(it) } } }
                it?.let { it.forecast?.let { it.forecastday?.let { it[0].hour?.let { it -> db.weatherInfoDao().insertHourWeather(it) } } } }
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