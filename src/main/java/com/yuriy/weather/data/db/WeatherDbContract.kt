package com.yuriy.weather.data.db

import android.provider.BaseColumns

object WeatherDbContract {

    object WeatherEntry : BaseColumns {
        const val TABLE_NAME = "weather"
        const val COLUMN_NAME_CITY = "city"
        const val COLUMN_NAME_UNIX_TIME = "unix_time"
        const val COLUMN_NAME_TEMPERATURE = "temperature"
        const val COLUMN_NAME_CONDITION = "condition"
        const val COLUMN_NAME_RAIN = "rain"
        const val COLUMN_MAME_WIND_SPEED = "wind_speed"
        const val COLUMN_NAME_WIND_DEGREE = "wind_degree"
    }

    const val SQL_CREATE_ENTRIES =
        "CREATE TABLE ${WeatherEntry.TABLE_NAME} (" +
                "${BaseColumns._ID} INTEGER PRIMARY KEY, " +
                "${WeatherEntry.COLUMN_NAME_CITY} TEXT, " +
                "${WeatherEntry.COLUMN_NAME_UNIX_TIME} INTEGER, " +
                "${WeatherEntry.COLUMN_NAME_TEMPERATURE} REAL," +
                "${WeatherEntry.COLUMN_NAME_CONDITION} TEXT, " +
                "${WeatherEntry.COLUMN_NAME_RAIN} TEXT, " +
                "${WeatherEntry.COLUMN_MAME_WIND_SPEED} REAL, " +
                "${WeatherEntry.COLUMN_NAME_WIND_DEGREE} INTEGER" +
                ")"

    const val SQL_DELETE_ENTRIES =
        "DROP TABLE IF EXIST ${WeatherEntry.TABLE_NAME}"
}