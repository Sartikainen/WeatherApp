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
import com.example.weatherapp.pojo.Hour
import com.example.weatherapp.pojo.WeatherInfo
import com.squareup.picasso.Picasso

class WeatherInfoAdapter(private val context: Context): RecyclerView.Adapter<WeatherInfoAdapter.WeatherInfoViewHolder>() {

    var weatherInfoListOfDays: List<WeatherInfo> = arrayListOf()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): WeatherInfoViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_weather_info, parent, false)
        return WeatherInfoViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: WeatherInfoViewHolder,
        position: Int,
    ) {

    }

    override fun getItemCount(): Int {
     return 0
    }

    inner class WeatherInfoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    }
}