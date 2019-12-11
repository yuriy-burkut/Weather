package com.yuriy.weather.data.network.responces

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.yuriy.weather.data.entities.City
import com.yuriy.weather.data.entities.MainWeather

@JsonClass(generateAdapter = true)
data class WeatherResponse(
    val id: Long,
    val main: MainWeather,
    @Json(name="name") val city : String
)