package com.yuriy.weather.data.network

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.yuriy.weather.data.WeatherFetcher
import com.yuriy.weather.data.network.responces.ForecastResponse
import com.yuriy.weather.data.network.responces.WeatherResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NetworkWeatherFetcher : WeatherFetcher {

    private val weatherApi = RetrofitService()
        .createService()

    companion object {
        @Volatile
        private var instance: NetworkWeatherFetcher? = null

        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: NetworkWeatherFetcher().also { instance = it }
            }
    }


    override fun fetchCurrentWeather(
        location: String,
        units: String
    ): MutableLiveData<WeatherResponse> {
        val weatherData: MutableLiveData<WeatherResponse> = MutableLiveData()
        weatherApi.getCurrentWeather(location, units).enqueue(object : Callback<WeatherResponse> {

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                Log.e("getCurrentWeather onFailure: ", t.toString())
            }

            override fun onResponse(
                call: Call<WeatherResponse>,
                response: Response<WeatherResponse>
            ) {
                if (response.isSuccessful) {
                    weatherData.value = response.body()
                    Log.e("getCurrentWeather onResponce: ", "succesfull")
                }
            }
        })

        return weatherData
    }

    override fun fetchWeatherForecast(
        location: String,
        units: String
    ): MutableLiveData<ForecastResponse> {
        val forecastData: MutableLiveData<ForecastResponse> = MutableLiveData()
        weatherApi.getWeatherForecast(location, units)
            .enqueue(object : Callback<ForecastResponse> {
                override fun onFailure(call: Call<ForecastResponse>, t: Throwable) {
                    Log.e("getWeatherForecast onFailure: ", t.toString())
                }

                override fun onResponse(
                    call: Call<ForecastResponse>,
                    response: Response<ForecastResponse>
                ) {
                    if (response.isSuccessful) {
                        forecastData.value = response.body()
                        Log.e("getWeatherForecast onResponce: ", "succesfull")
                    }
                }
            })

        return forecastData
    }
}