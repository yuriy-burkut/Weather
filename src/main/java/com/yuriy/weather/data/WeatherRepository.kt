package com.yuriy.weather.data

import androidx.lifecycle.MutableLiveData
import com.yuriy.weather.application.AppClass
import com.yuriy.weather.data.db.WeatherDBFetcher
import com.yuriy.weather.data.db.WeatherDBWriter
import com.yuriy.weather.data.network.NetworkWeatherFetcher
import com.yuriy.weather.data.network.responces.ForecastResponse
import com.yuriy.weather.data.network.responces.WeatherResponse
import com.yuriy.weather.preferences.SPHelper

class WeatherRepository {

    companion object {
        @Volatile
        private var instance: WeatherRepository? = null

        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: WeatherRepository().also { instance = it }
            }
    }

    private val weatherData = MutableLiveData<WeatherResponse>()
    private val forecastData = MutableLiveData<ForecastResponse>()

    private var location = SPHelper.getCity()
    private var units = SPHelper.getUnits()

    fun getCurrentWeather(): MutableLiveData<WeatherResponse> {
        return fetchCurrentWeather(location, units)
    }

    fun getWeatherForecast(): MutableLiveData<ForecastResponse> {
        return fetchWeatherForecast(location, units)
    }

    fun updateData() {
        location = SPHelper.getCity()
        units = SPHelper.getUnits()
        weatherData.value = fetchCurrentWeather(location, units).value
        forecastData.value = fetchWeatherForecast(location, units).value
    }

    private fun fetchCurrentWeather(
        location: String,
        units: String
    ): MutableLiveData<WeatherResponse> {
        if (AppClass.isOnline()) {
            val weather = NetworkWeatherFetcher.getInstance().fetchCurrentWeather(location, units)
            if (weather.value != null) {
                WeatherDBWriter.writeCurrentWeather(weather.value!!)
            }
            return weather
        } else {

            return WeatherDBFetcher.getInstance().fetchCurrentWeather(location, units)
        }
    }

    private fun fetchWeatherForecast(
        location: String,
        units: String
    ): MutableLiveData<ForecastResponse> {
        val response = MutableLiveData<ForecastResponse>()
        if (AppClass.isOnline()) {
            return NetworkWeatherFetcher.getInstance().fetchWeatherForecast(location, units)
        } else {
            //WeatherDBFetcher.getInstance().fetchWeatherForecast(location, units)
            return NetworkWeatherFetcher.getInstance().fetchWeatherForecast(location, units)
        }

        // return response
    }
}