package com.dov.templateapp.webservice.common

import com.dov.templateapp.BuildConfig
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import org.jetbrains.annotations.NotNull
import java.util.concurrent.TimeUnit

/**
 * Helper class for BaseService class
 */

class BaseServiceHelper {
    companion object {
        private const val SERVICE_CONNECTION_TIMEOUT: Long = 20
        private const val SERVICE_READ_TIMEOUT: Long = 40

        /**
         * Returns an httpClient for Retrofit
         */
        fun getHttpClient(@NotNull headersMap: Map<String, String>): OkHttpClient.Builder {
            val httpClient = OkHttpClient.Builder()
            httpClient.addInterceptor(object : Interceptor {
                override fun intercept(chain: Interceptor.Chain): Response {
                    val original: Request = chain.request()
                    val requestBuilder = original.newBuilder()
                        .method(original.method(), original.body())
                    if (headersMap.isNotEmpty()) {
                        requestBuilder.headers(Headers.of(headersMap))
                    }
                    val request: Request = requestBuilder
                        .build()


                    return chain.proceed(request)
                    // val response = chain.proceed(request)
                   // if (response.code() == 500) { // we may deal we some http error code here if needed
                }


            })

            if (BuildConfig.DEBUG) {
                val logging = HttpLoggingInterceptor()
                logging.level = HttpLoggingInterceptor.Level.BODY
                httpClient.addInterceptor(logging)
            }
            httpClient.connectTimeout(SERVICE_CONNECTION_TIMEOUT, TimeUnit.SECONDS)
            httpClient.readTimeout(SERVICE_READ_TIMEOUT, TimeUnit.SECONDS)
            return httpClient
        }
    }
}