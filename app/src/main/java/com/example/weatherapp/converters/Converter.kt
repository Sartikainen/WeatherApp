package com.example.weatherapp.converters

import androidx.room.TypeConverter
import com.example.weatherapp.pojo.Forecastday
import com.example.weatherapp.pojo.Hour
import com.google.gson.Gson
import org.json.JSONArray
import org.json.JSONObject

class Converter {
    @TypeConverter
    fun listHoursToString(hours: List<Hour>): String {
        var jsonArray = JSONArray()
        for (hour in hours) {
            var jsonObject = JSONObject()
            jsonObject.put("time", hour.time)
            jsonObject.put("temp_c", hour.tempC)
            jsonArray.put(jsonObject)
        }
        return jsonArray.toString()
    }

        @TypeConverter
        fun stringToListHours(hoursAsString: String): List<Hour> {
            var gson = Gson()
            var objects: ArrayList<JSONObject> =
                gson.fromJson(hoursAsString, Array<JSONObject>::class.java)
                    .toList() as ArrayList<JSONObject>
            var hours: ArrayList<Hour> = ArrayList()
            for (o in objects) {
                hours.add(gson.fromJson(o.toString(), Hour::class.java))
            }
            return hours
        }
    }