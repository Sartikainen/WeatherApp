package com.example.weatherapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.weatherapp.data.database.model.WeatherInfoDbModel
import com.example.weatherapp.pojo.Condition
import com.example.weatherapp.pojo.ConditionHour
import com.example.weatherapp.pojo.Current
import com.example.weatherapp.pojo.Forecastday
import com.example.weatherapp.pojo.Hour
import com.example.weatherapp.pojo.Location

@Database(entities = [Location::class, Current::class, Forecastday::class, Hour::class, Condition::class, WeatherInfoDbModel::class, ConditionHour::class], version = 8, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    companion object {

        private var db: AppDatabase? = null
        private const val DB_NAME = "main.db"
        private val LOCK = Any()

        fun getInstance(context: Context): AppDatabase {
            synchronized(LOCK) {
                db?.let { return it }
                val instance =
                    Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME).fallbackToDestructiveMigration().build()
                db = instance
                return instance
            }
        }
    }

    abstract fun weatherInfoDao(): WeatherInfoDao
}