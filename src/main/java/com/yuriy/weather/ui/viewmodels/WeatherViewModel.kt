package com.yuriy.weather.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.yuriy.weather.data.WeatherRepository

class WeatherViewModel(private val weatherRepository: WeatherRepository) : ViewModel() {

    var currentWeather = weatherRepository.currentWeather

    var weatherForecast = weatherRepository.weatherForecast

}