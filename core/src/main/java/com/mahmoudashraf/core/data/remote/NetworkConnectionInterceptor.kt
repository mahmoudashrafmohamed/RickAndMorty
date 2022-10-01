package com.mahmoudashraf.core.data.remote

import android.content.Context
import com.mahmoudashraf.core.androidextensions.isInternetAvailable
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException


class NetworkConnectionInterceptor(private val appContext: Context) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isConnected) {
            // Throwing our custom exception 'NoConnectivityException'
            throw NoConnectivityException()
        }
        val builder: Request.Builder = chain.request().newBuilder()
        return chain.proceed(builder.build())
    }

   private val isConnected: Boolean
        get() {
          return appContext.isInternetAvailable()
        }

}

class NoConnectivityException : IOException() {
    // You can send any message whatever you want from here.
    override val message: String
        get() = "No Internet Connection"
}