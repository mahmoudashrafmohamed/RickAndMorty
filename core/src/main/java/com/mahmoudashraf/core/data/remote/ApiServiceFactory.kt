package com.mahmoudashraf.core.data.remote

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
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

    inline fun <reified T> create(isDebug: Boolean, context: Context, baseUrl: String): T {
        val retrofit = createRetrofit(isDebug, context, baseUrl)
        return retrofit.create(T::class.java)
    }

    fun createRetrofit(isDebug: Boolean, context: Context, baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(
                createOkHttpClient(
                    createLoggingInterceptor(isDebug),
                    createPrettyLoggingInterceptor(isDebug),
                    createChuckerInterceptor(context),
                    createNetworkConnectionInterceptor(context)
                )
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    private fun createOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        loggingInterceptor: LoggingInterceptor,
        chuckerInterceptor: ChuckerInterceptor,
        networkConnectionInterceptor: NetworkConnectionInterceptor

    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(loggingInterceptor)
            .addInterceptor(chuckerInterceptor)
            .addInterceptor(networkConnectionInterceptor)
            .connectTimeout(OK_HTTP_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(OK_HTTP_TIMEOUT, TimeUnit.SECONDS)
            .dispatcher(Dispatcher().apply { maxRequestsPerHost = 20 })
            .build()
    }

    private fun createChuckerInterceptor(context: Context) =
        ChuckerInterceptor.Builder(context)
            .maxContentLength(250_000L)
            .build()

    private fun createNetworkConnectionInterceptor(context: Context) =
        NetworkConnectionInterceptor(context)

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
