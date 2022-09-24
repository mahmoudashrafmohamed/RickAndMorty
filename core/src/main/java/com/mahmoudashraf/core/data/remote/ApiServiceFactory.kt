package com.mahmoudashraf.core.data.remote

import com.ihsanbal.logging.Level
import com.ihsanbal.logging.LoggingInterceptor
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import okhttp3.internal.platform.Platform
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiServiceFactory {

    inline fun <reified T> create(isDebug: Boolean, baseUrl: String): T {
        val retrofit = createRetrofit(isDebug, baseUrl)
        return retrofit.create(T::class.java)
    }

    fun createRetrofit(isDebug: Boolean, baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(createOkHttpClient(createLoggingInterceptor(isDebug), createPrettyLoggingInterceptor(isDebug)))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }



    private fun createOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor, loggingInterceptor: LoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(loggingInterceptor)
            .connectTimeout(OK_HTTP_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(OK_HTTP_TIMEOUT, TimeUnit.SECONDS)
            .dispatcher(Dispatcher().apply { maxRequestsPerHost = 20 })
            .build()
    }

    private fun createLoggingInterceptor(isDebug: Boolean): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = if (isDebug) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }
    }

    private fun createPrettyLoggingInterceptor(isDebug: Boolean): LoggingInterceptor {
        val level = if (isDebug) Level.BODY
         else Level.NONE
        return LoggingInterceptor.Builder()
            .setLevel(level)
            .log(Platform.INFO)
            .build()
    }

    private const val OK_HTTP_TIMEOUT = 60L
}
