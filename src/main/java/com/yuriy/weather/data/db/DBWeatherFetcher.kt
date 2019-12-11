package com.yuriy.weather.data.db

import androidx.lifecycle.MutableLiveData
import com.yuriy.weather.data.WeatherFetcher
import com.yuriy.weather.data.network.responces.ForecastResponse
import com.yuriy.weather.data.network.responces.WeatherResponse

class DBWeatherFetcher : WeatherFetcher {

    companion object {
        @Volatile
        private var instance: DBWeatherFetcher? = null

        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: DBWeatherFetcher().also { instance = it }
            }
    }

    override fun fetchCurrentWeather(
        location: String,
        units: String
    ): MutableLiveData<WeatherResponse> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun fetchWeatherForecast(
        location: String,
        units: String
    ): MutableLiveData<ForecastResponse> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}