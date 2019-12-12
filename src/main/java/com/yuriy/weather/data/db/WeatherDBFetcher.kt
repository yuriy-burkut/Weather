package com.yuriy.weather.data.db

import android.provider.BaseColumns
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.yuriy.weather.application.AppClass
import com.yuriy.weather.data.WeatherFetcher
import com.yuriy.weather.data.db.WeatherDbContract.WeatherEntry
import com.yuriy.weather.data.entities.MainWeather
import com.yuriy.weather.data.entities.WeatherInfo
import com.yuriy.weather.data.entities.Wind
import com.yuriy.weather.data.network.responces.ForecastResponse
import com.yuriy.weather.data.network.responces.WeatherResponse

class WeatherDBFetcher : WeatherFetcher {

    companion object {
        @Volatile
        private var instance: WeatherDBFetcher? = null

        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: WeatherDBFetcher().also { instance = it }
            }
    }

    override fun fetchCurrentWeather(
        location: String,
        units: String
    ): MutableLiveData<WeatherResponse> {

        val db = AppClass.getDbHelper().writableDatabase

        val projection = arrayOf(
            BaseColumns._ID,
            WeatherEntry.COLUMN_NAME_CITY,
            WeatherEntry.COLUMN_NAME_TEMPERATURE,
            WeatherEntry.COLUMN_NAME_CONDITION
        )

        val selection = "${WeatherEntry.COLUMN_NAME_CITY} = ?"
        val selectionArgs = arrayOf(location)
        val sortedOrder = "${WeatherEntry.COLUMN_NAME_CITY} DESC"

        val cursor = db.query(
            WeatherEntry.TABLE_NAME,
            projection,
            selection,
            selectionArgs,
            null,
            null,
            sortedOrder
        )

        val items = mutableListOf<WeatherResponse>()

        with(cursor) {
            while (moveToNext()) {
                val itemId = getLong(getColumnIndexOrThrow(BaseColumns._ID))
                val city = getString(getColumnIndexOrThrow(WeatherEntry.COLUMN_NAME_CITY))
                val temp = getDouble(getColumnIndexOrThrow(WeatherEntry.COLUMN_NAME_TEMPERATURE))
                val condition = getString(getColumnIndexOrThrow(WeatherEntry.COLUMN_NAME_CONDITION))
                val weather = WeatherResponse(
                    MainWeather(itemId.toInt(), 0, temp, 0.0, 0.0), city,
                    listOf(WeatherInfo("", "", 0, "")), Wind(0, 0.0)
                )
                items.add(weather)
                Log.e("Weather db fetch: ", "Cursor block was used. Items size is ${items.size} ")
            }
        }

        cursor.close()

        return if (items.isNotEmpty())
            MutableLiveData(items[items.lastIndex])
        else
            MutableLiveData(
                WeatherResponse(
                    MainWeather(0, 0, 1000.0, 0.0, 0.0), "Null",
                    listOf<WeatherInfo>(WeatherInfo("", "", 0, "null")), Wind(0, 0.0)
                )
            )
    }

    override fun fetchWeatherForecast(
        location: String,
        units: String
    ): MutableLiveData<ForecastResponse> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}