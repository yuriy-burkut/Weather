package com.yuriy.weather.data

import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import androidx.preference.PreferenceManager
import com.yuriy.weather.application.AppClass
import com.yuriy.weather.data.db.DBWeatherFetcher
import com.yuriy.weather.data.network.NetworkWeatherFetcher
import com.yuriy.weather.data.network.responces.ForecastResponse
import com.yuriy.weather.data.network.responces.WeatherResponse

class WeatherRepository {

    companion object {
        @Volatile
        private var instance: WeatherRepository? = null

        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: WeatherRepository().also { instance = it }
            }
    }

    private val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(AppClass.applicationContext())

    var currentWeather = fetchCurrentWeather()
    var weatherForecast = fetchWeatherForecast()

    fun updateData() {
        currentWeather = fetchCurrentWeather()
        weatherForecast = fetchWeatherForecast()
    }

    private fun fetchCurrentWeather(): MutableLiveData<WeatherResponse> {
        return if (AppClass.isOnline()) {
            NetworkWeatherFetcher.getInstance().fetchCurrentWeather(getCity(), getUnits())
        } else {
            DBWeatherFetcher.getInstance().fetchCurrentWeather(getCity(), getUnits())
        }

    }

    private fun fetchWeatherForecast(): MutableLiveData<ForecastResponse> {
        return if (AppClass.isOnline()) {
            NetworkWeatherFetcher.getInstance().fetchWeatherForecast(getCity(), getUnits())
        } else {
            DBWeatherFetcher.getInstance().fetchWeatherForecast(getCity(), getUnits())
        }
    }

    private fun getUnits(): String {
        return sharedPreferences.getString("units", "metric") ?: "metric"
    }

    private fun getCity(): String {
        return sharedPreferences.getString("city" , "Cherkasy,ua") ?: "Cherkasy,ua"
    }
}