package com.yuriy.weather.data.db

import android.content.ContentValues
import android.util.Log
import com.yuriy.weather.application.AppClass
import com.yuriy.weather.data.db.WeatherDbContract.WeatherEntry
import com.yuriy.weather.data.network.responces.WeatherResponse

object WeatherDBWriter {

    fun writeCurrentWeather(weather: WeatherResponse) {

        val db = AppClass.getDbHelper().writableDatabase


        val values = ContentValues().apply {
            put(WeatherEntry.COLUMN_NAME_CITY, weather.city)
            put(WeatherEntry.COLUMN_NAME_TEMPERATURE, weather.main.temp)
            //put(WeatherEntry.COLUMN_NAME_CONDITION, weather.value?.weather?[0].conditions)
        }

        val newRowId = db?.insert(WeatherEntry.TABLE_NAME, null, values)

        Log.e("Weather db write: ", "Function was called, rowId is $newRowId")
    }
}