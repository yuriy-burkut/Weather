package com.yuriy.weather.data

import androidx.lifecycle.MutableLiveData
import com.yuriy.weather.data.network.responces.ForecastResponse
import com.yuriy.weather.data.network.responces.WeatherResponse

interface WeatherFetcher {
    fun fetchCurrentWeather(location: String, units: String): MutableLiveData<WeatherResponse>
    fun fetchWeatherForecast(location: String, units: String): MutableLiveData<ForecastResponse>
}