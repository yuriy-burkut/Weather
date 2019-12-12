package com.yuriy.weather.preferences

import androidx.preference.PreferenceManager
import com.yuriy.weather.application.AppClass

object SPHelper {

    private val sharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(AppClass.applicationContext())

    fun getUnits(): String {
        return sharedPreferences.getString("units", "metric") ?: "metric"
    }

    fun getCity(): String {
        return sharedPreferences.getString("city", "Cherkasy,ua") ?: "Cherkasy,ua"
    }

}