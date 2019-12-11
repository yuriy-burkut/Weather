package com.yuriy.weather.data.entities

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ForecastItem(
    @Json(name = "clouds") val clouds: Clouds,
    @Json(name = "dt") val unixTime: Int,
    @Json(name = "dt_txt") val dtTxt: String,
    @Json(name = "main") val main: MainWeather,
    @Json(name = "weather") val weatherInfo: List<WeatherInfo>,
    @Json(name = "rain") val rain: Rain?,
    @Json(name = "snow") val snow: Snow?,
    @Json(name = "wind") val wind: Wind
)