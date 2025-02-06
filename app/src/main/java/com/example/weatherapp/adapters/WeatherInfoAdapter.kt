package com.example.weatherapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.pojo.Hour
import com.example.weatherapp.pojo.WeatherInfo

class WeatherInfoAdapter: RecyclerView.Adapter<WeatherInfoAdapter.WeatherInfoViewHolder>() {

    var weatherInfoList = arrayListOf<WeatherInfo>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): WeatherInfoViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.activity_main, parent, false)
        return WeatherInfoViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: WeatherInfoViewHolder,
        position: Int,
    ) {
        val weatherInfo = weatherInfoList[position]
        holder.tvCity.text = weatherInfo.location?.name
        holder.tvTemperature.text = weatherInfo.current?.tempC.toString()
        holder.tvWind.text = weatherInfo.current?.windKph.toString()
        holder.tvPressure.text = weatherInfo.current?.pressureMb.toString()
        holder.tvHumidity.text = weatherInfo.current?.humidity.toString()
        holder.tvDescriptionWeather.text = weatherInfo.current?.condition?.text
    }

    override fun getItemCount() = weatherInfoList.size

    inner class WeatherInfoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvCity: TextView = view.findViewById(R.id.tvCity)
        val tvTemperature: TextView = view.findViewById(R.id.tvTemperature)
        val tvWind: TextView = view.findViewById(R.id.tvWind)
        val tvPressure: TextView = view.findViewById(R.id.tvPressure)
        val tvHumidity: TextView = view.findViewById(R.id.tvHumidity)
        val ivCurrentWeather: ImageView = view.findViewById(R.id.ivCurrentWeather)
        val tvDescriptionWeather: TextView = view.findViewById(R.id.tvDescriptionWeather)
    }
}