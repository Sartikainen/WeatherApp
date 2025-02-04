package com.example.weatherapp

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.weatherapp.api.ApiFactory
import com.example.weatherapp.database.AppDatabase
import com.example.weatherapp.pojo.Hour
import com.example.weatherapp.pojo.Location
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class WeatherViewModel(application: Application) : AndroidViewModel(application) {

    private val db = AppDatabase.getInstance(application)
    private val compositeDisposable = CompositeDisposable()

    val currentWeather = db.weatherInfoDao().getCurrentWeather()
    val forecastWeatherPerHour = db.weatherInfoDao().getForecastWeatherPerHour()

    fun getInfoAboutCity(name: String): LiveData<Location> {
        return db.weatherInfoDao().getInfoAboutCity(name)
    }

    fun getTempFromHour(time: String): LiveData<Hour> {
        return db.weatherInfoDao().getTempFromHour(time)
    }

    fun loadData() {
        val disposable = ApiFactory.apiService.getWeatherInfo(city = "Tbilisi", days = 3)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
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