package com.yuriy.weather.application

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

class AppClass : Application() {

    init {
        instance = this
    }

    companion object {
        private var instance : AppClass? = null

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

    }

    override fun onCreate() {
        super.onCreate()
    }
}