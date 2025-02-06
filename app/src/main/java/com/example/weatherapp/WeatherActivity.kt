package com.example.weatherapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.api.ApiFactory.BASE_IMAGE_URL
import com.example.weatherapp.pojo.WeatherInfo
import com.squareup.picasso.Picasso

class WeatherActivity : AppCompatActivity() {

    private lateinit var viewModel: WeatherViewModel
    private lateinit var tvCity: TextView
    private lateinit var tvTemperature: TextView
    private lateinit var tvWind: TextView
    private lateinit var tvPressure: TextView
    private lateinit var tvHumidity: TextView
    private lateinit var tvDescriptionWeather: TextView
    private lateinit var ivCurrentWeather: ImageView
    private lateinit var tvTime: TextView
    private lateinit var tvDegrees: TextView

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.item_weather_info)
        tvCity = findViewById(R.id.tvCity)
        tvTemperature = findViewById(R.id.tvTemperature)
        tvWind = findViewById(R.id.tvWind)
        tvPressure = findViewById(R.id.tvPressure)
        tvHumidity = findViewById(R.id.tvHumidity)
        tvDescriptionWeather = findViewById(R.id.tvDescriptionWeather)
        ivCurrentWeather = findViewById(R.id.ivCurrentWeather)
        tvTime = findViewById(R.id.tvTime)
        tvDegrees = findViewById(R.id.tvDegrees)
        viewModel = ViewModelProvider(this)[WeatherViewModel::class.java]
        viewModel.location().observe(this, Observer {
            tvCity.text = it.name
        })
        viewModel.infoAboutIcon().observe(this, Observer {
            tvDescriptionWeather.text = it.text
            Picasso.get().load("https:${it.icon}").into(ivCurrentWeather)
        })
//        viewModel.currentWeather().observe(this, Observer {
//            tvTemperature.text = it.tempC.toString()
//            tvWind.text = it.windKph.toString()
//            tvPressure.text = it.pressureMb.toString()
//            tvHumidity.text = it.humidity.toString()
//        })
        viewModel.infoAboutIcon().observe(this, Observer {
            tvDescriptionWeather.text = it.text
            Picasso.get().load("$BASE_IMAGE_URL${it.icon}").into(ivCurrentWeather)
        })
        viewModel.tempPerHour().observe(this, Observer {
            tvTime.text = it[0].time
            tvDegrees.text = it[0].tempC.toString()
            Log.d("TEST_OF_LOADING_DATA", "Activity: $it")
        })
    }
}
