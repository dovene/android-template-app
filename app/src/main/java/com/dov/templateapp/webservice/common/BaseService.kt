package com.dov.templateapp.webservice.common

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Base class for all service classes to extend
 */
abstract class BaseService {
    abstract fun getRootUrl(): String
    abstract fun getApiClass(): Class<*>

    fun getApi(): Any {
        return getRetrofit().create(getApiClass())
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(getRootUrl())
            .addCallAdapterFactory(RxErrorCallHandlingAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(BaseServiceHelper.getHttpClient(getHeaders()).build())
            .build()
    }

     open fun getHeaders(): Map<String, String> {
        return emptyMap()
    }
}