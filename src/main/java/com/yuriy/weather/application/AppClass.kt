package com.yuriy.weather.application

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.yuriy.weather.data.db.WeatherDbHelper

class AppClass : Application() {

    init {
        instance = this
    }

    companion object {
        private var instance : AppClass? = null
        private var dbHelper : WeatherDbHelper? = null

        fun applicationContext() : Context {
            return instance!!.applicationContext
        }

        fun isOnline(): Boolean {
            val connectivityManager = instance!!.getSystemService(Context.CONNECTIVITY_SERVICE)
                    as ConnectivityManager
            val networkInfo =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            return networkInfo != null && networkInfo.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        }

        fun getDbHelper() : WeatherDbHelper {
            return dbHelper!!
        }
    }

    override fun onCreate() {
        super.onCreate()
        dbHelper = WeatherDbHelper(applicationContext)
    }
}