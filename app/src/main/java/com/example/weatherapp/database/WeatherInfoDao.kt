package com.example.weatherapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.weatherapp.pojo.Current
import com.example.weatherapp.pojo.Forecastday
import com.example.weatherapp.pojo.Hour
import com.example.weatherapp.pojo.Location

@Dao
interface WeatherInfoDao {

    @Query("SELECT * FROM location_values WHERE name = :name")
    fun getInfoAboutCity(name: String): LiveData<Location>

    @Query("SELECT * FROM current_values")
    fun getCurrentWeather(): LiveData<Current>

    @Query("SELECT * FROM forecast_values")
    fun getForecastWeatherPerHour(): LiveData<List<Forecastday>>

    @Query("SELECT * FROM hour_values WHERE time = :time")
    fun getTempFromHour(time: String): LiveData<List<Hour>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLocation(location: Location)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCurrentWeather(current: Current)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertForecastdayWeather(forecastday: List<Forecastday>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertHourWeather(hour: List<Hour>)
}