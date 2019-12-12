package com.yuriy.weather.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.yuriy.weather.data.WeatherRepository

class WeatherViewModel(private val weatherRepository: WeatherRepository) : ViewModel() {

    fun getWeather() = weatherRepository.getCurrentWeather()
    fun getForecast() = weatherRepository.getWeatherForecast()
}