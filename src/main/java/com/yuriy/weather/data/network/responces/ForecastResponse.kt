package com.yuriy.weather.data.network.responces

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.yuriy.weather.data.entities.City
import com.yuriy.weather.data.entities.ForecastItem

@JsonClass(generateAdapter = true)
data class ForecastResponse(
    @Json(name = "city") val city: City,
    @Json(name = "cnt") val cnt: Int,
    @Json(name = "cod") val cod: String,
    @Json(name = "list") val list: List<ForecastItem>,
    @Json(name = "message") val message: Int
)
