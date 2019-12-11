package com.yuriy.weather.data.network

import com.yuriy.weather.data.network.responces.ForecastResponse
import com.yuriy.weather.data.network.responces.WeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("weather")
    fun getCurrentWeather(@Query("q") city: String, @Query("units") units: String): Call<WeatherResponse>

    @GET("forecast")
     fun getWeatherForecast(@Query("q") city: String, @Query("units") units: String): Call<ForecastResponse>

}