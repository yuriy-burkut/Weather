package com.yuriy.weather.data.network.responces

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.yuriy.weather.data.entities.City
import com.yuriy.weather.data.entities.MainWeather
import com.yuriy.weather.data.entities.WeatherInfo
import com.yuriy.weather.data.entities.Wind

@JsonClass(generateAdapter = true)
data class WeatherResponse(
    val main: MainWeather,
    @Json(name="name") val city : String,
    val weather: List<WeatherInfo>,
    val wind: Wind
)