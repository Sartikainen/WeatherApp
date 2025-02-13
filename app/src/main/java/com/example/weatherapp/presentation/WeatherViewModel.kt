package com.example.weatherapp.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.weatherapp.data.repository.WeatherInfoRepositoryImpl
import com.example.weatherapp.domain.FetchWeatherInfoUseCase
import com.example.weatherapp.domain.GetWeatherInfoUseCase
import com.example.weatherapp.pojo.WeatherInfo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class WeatherViewModel(application: Application) : AndroidViewModel(application) {

    private val compositeDisposable = CompositeDisposable()
    private val weatherInfoRepository = WeatherInfoRepositoryImpl(application)
    private val getWeatherInfoUseCase = GetWeatherInfoUseCase(weatherInfoRepository)
    private val fetchWeatherInfoUseCase = FetchWeatherInfoUseCase(weatherInfoRepository)

    private var city: String = "Северск"
    private var days: Int = 3

    private val _weatherInfo: MutableLiveData<WeatherInfo> = MutableLiveData(null)
    val weatherInfo: LiveData<WeatherInfo>
        get() = _weatherInfo

    fun setCity(newCity: String) {
        if (city != newCity) {
            city = newCity
        }
        fetchWeatherInfo()
    }

    init {
        fetchWeatherInfo()
    }

    private fun fetchWeatherInfo() {
            val disposable = fetchWeatherInfoUseCase.execute(city.toString(), days)
                .subscribeOn(Schedulers.io())
                .subscribe()
            getWeatherInfo()

            compositeDisposable.add(disposable)
    }

    private fun getWeatherInfo() {
        val disposable = getWeatherInfoUseCase.execute(city.toString())
            .delaySubscription(1000, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                _weatherInfo.value = it
            }

        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}