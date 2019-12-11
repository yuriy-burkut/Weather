package com.yuriy.weather.data.network.interceptors

import com.yuriy.weather.application.AppClass
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class ConnectivityInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        if (!AppClass.isOnline()) {
            throw IOException()
        }
        return chain.proceed(chain.request())
    }

}