package com.example.weatherapp.presentation

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.adapters.WeatherInfoAdapter
import com.example.weatherapp.data.api.ApiFactory.BASE_IMAGE_URL
import com.example.weatherapp.pojo.Hour
import com.example.weatherapp.utils.EMPTY
import com.squareup.picasso.Picasso

class WeatherActivity : AppCompatActivity() {

    private lateinit var viewModel: WeatherViewModel
    private lateinit var rvWeatherHourList: RecyclerView
    private lateinit var adapter: WeatherInfoAdapter

    private lateinit var tvCity: TextView
    private lateinit var tvLocaltime: TextView
    private lateinit var tvTemperature: TextView
    private lateinit var tvWind: TextView
    private lateinit var tvPressure: TextView
    private lateinit var tvHumidity: TextView
    private lateinit var tvDescriptionWeather: TextView
    private lateinit var ivCurrentWeather: ImageView
    private lateinit var etCity: EditText
    private lateinit var tvSendCity: TextView

    private lateinit var radioGroup: RadioGroup

    @SuppressLint("SetTextI18n", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rvWeatherHourList = findViewById(R.id.rvWeatherHourInfo)
        radioGroup = findViewById(R.id.radioGroup)
        tvCity = findViewById(R.id.tvCity)
        tvLocaltime = findViewById(R.id.tvLocaltime)
        tvTemperature = findViewById(R.id.tvTemperature)
        tvWind = findViewById(R.id.tvWind)
        tvPressure = findViewById(R.id.tvPressure)
        tvHumidity = findViewById(R.id.tvHumidity)
        tvDescriptionWeather = findViewById(R.id.tvDescriptionWeather)
        ivCurrentWeather = findViewById(R.id.ivCurrentWeather)
        etCity = findViewById(R.id.etCity)
        tvSendCity = findViewById(R.id.tvSendCity)
        adapter = WeatherInfoAdapter(this)
        adapter.weatherInfoListOfDays = ArrayList<Hour>()
        rvWeatherHourList.adapter = adapter
        viewModel = ViewModelProvider(this)[WeatherViewModel::class.java]
        observeData()
    }

    @SuppressLint("SetTextI18n", "NotifyDataSetChanged")
    private fun observeData() {
        viewModel.weatherInfo.observe(this, Observer {
            if (it != null) {
                if(adapter.weatherInfoListOfDays.isEmpty()) {
                    adapter.weatherInfoListOfDays = it.forecast?.forecastday?.get(0)?.hour ?: ArrayList<Hour>()
                }
                tvCity.text = it.location?.name ?: String.EMPTY
                tvLocaltime.text = it.location?.localtime ?: String.EMPTY
                tvTemperature.text = "${it.current?.tempC ?: String.EMPTY}°C"
                tvWind.text = "${it.current?.windKph ?: String.EMPTY} km/h"
                tvPressure.text = "${it.current?.pressureMb ?: String.EMPTY} hPa"
                tvHumidity.text = "${it.current?.humidity ?: String.EMPTY}%"
                tvDescriptionWeather.text = it.current?.condition?.text ?: String.EMPTY
                Picasso.get().load("$BASE_IMAGE_URL${it.current?.condition?.icon}")
                    .into(ivCurrentWeather)
                radioGroup.setOnCheckedChangeListener { _, checkedId ->
                    when (checkedId) {
                        R.id.rbToday -> adapter.weatherInfoListOfDays =
                            it.forecast?.forecastday?.get(0)?.hour ?: ArrayList<Hour>()

                        R.id.rbTomorrow -> adapter.weatherInfoListOfDays =
                            it.forecast?.forecastday?.get(1)?.hour ?: ArrayList<Hour>()

                        R.id.rbThreeDays -> {
                            var temp: MutableList<Hour> = arrayListOf()
                            for (i in 0..2) {
                                temp.addAll(it.forecast?.forecastday?.get(i)?.hour ?: ArrayList<Hour>())
                            }
                            adapter.weatherInfoListOfDays = temp
                        }
                    }
                    adapter.notifyDataSetChanged()
                }
            }
        })
    }
}
