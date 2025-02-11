package com.example.weatherapp.converters

import androidx.room.TypeConverter
import com.example.weatherapp.pojo.ConditionHour
import com.google.gson.Gson
import org.json.JSONObject

class ConverterConditionHour {

    @TypeConverter
    fun conditionToString(condition: ConditionHour): String {
        var jsonObject = JSONObject()
        jsonObject.put("text", condition.text)
        jsonObject.put("icon", condition.icon)
        jsonObject.put("code", condition.code)
        return jsonObject.toString()
    }

    @TypeConverter
    fun stringToCondition(conditionAsString: String): ConditionHour {
        var gson = Gson()
        var objects: JSONObject =
            gson.fromJson(conditionAsString, JSONObject::class.java)
        var condition: ConditionHour = ConditionHour()
        condition.text = objects.getString("text")
        condition.icon = objects.getString("icon")
        condition.code = objects.getInt("code")
        return condition
    }
}