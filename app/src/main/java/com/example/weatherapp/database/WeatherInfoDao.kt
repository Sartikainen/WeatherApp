package com.example.weatherapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.weatherapp.pojo.Condition
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

    @Query("SELECT * FROM forecast_values WHERE date = :date")
    fun getForecastWeatherPerHour(date: String): LiveData<List<Forecastday>>

    @Query("SELECT * FROM hour_values")
    fun getTempFromHour(): LiveData<List<Hour>>

    @Query("SELECT * FROM condition_values")
    fun getInfoAboutIcon(): LiveData<Condition>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLocation(location: Location)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCurrentWeather(current: Current)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertForecastdayWeather(forecastday: List<Forecastday>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertHourWeather(hour: List<Hour>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCondition(condition: Condition)
}