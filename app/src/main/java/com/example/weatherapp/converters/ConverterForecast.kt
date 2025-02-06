package com.example.weatherapp.converters

import androidx.room.TypeConverter
import com.example.weatherapp.pojo.Forecastday
import com.example.weatherapp.pojo.Hour
import com.google.gson.Gson
import org.json.JSONArray
import org.json.JSONObject

class ConverterForecast {
    @TypeConverter
    fun listForecastToString(forecasts: List<Forecastday>): String {
        var jsonArray = JSONArray()
        for (forecast in forecasts) {
            var jsonObject = JSONObject()
            forecast.hour?.let {
                for (hour in forecast.hour) {
                    jsonObject.put("time", hour.time)
                    jsonObject.put("temp_c", hour.tempC)
                    jsonArray.put(jsonObject)
                }
            }
            jsonObject.put("date", forecast.date)
            jsonArray.put(jsonObject)
        }
        return jsonArray.toString()
    }

    @TypeConverter
    fun stringToListForecast(forecastsAsString: String): List<Forecastday> {
        var gson = Gson()
        var objects: ArrayList<JSONObject> =
            gson.fromJson(forecastsAsString, Array<JSONObject>::class.java)
                .toList() as ArrayList<JSONObject>
        var forecasts: ArrayList<Forecastday> = ArrayList()
        for (o in objects) {
            forecasts.add(gson.fromJson(o.toString(), Forecastday::class.java))
        }
        return forecasts
    }
}