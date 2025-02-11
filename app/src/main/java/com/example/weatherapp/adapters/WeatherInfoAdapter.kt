package com.example.weatherapp.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.data.api.ApiFactory.BASE_IMAGE_URL
import com.example.weatherapp.pojo.Hour
import com.squareup.picasso.Picasso

class WeatherInfoAdapter(private val context: Context) :
    RecyclerView.Adapter<WeatherInfoAdapter.WeatherInfoViewHolder>() {

    var weatherInfoListOfDays: List<Hour> = arrayListOf()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): WeatherInfoViewHolder {
        var view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_weather_info, parent, false)
        return WeatherInfoViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: WeatherInfoViewHolder,
        position: Int,
    ) {
        val hour = weatherInfoListOfDays[position]
        holder.tvTime.text = hour.time
        holder.tvDegrees.text = hour.tempC.toString()
        Picasso.get().load("${BASE_IMAGE_URL}${hour.condition?.icon}")
            .into(holder.ivForecastWeather)

    }

    override fun getItemCount(): Int {
        return weatherInfoListOfDays.size
    }

    inner class WeatherInfoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tvTime: TextView = view.findViewById(R.id.tvTime)
        var tvDegrees: TextView = view.findViewById(R.id.tvDegrees)
        var ivForecastWeather: ImageView = view.findViewById(R.id.ivForecastWeather)
    }
}