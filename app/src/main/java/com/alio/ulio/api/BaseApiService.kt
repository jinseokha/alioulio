package com.alio.ulio.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @author Ha Jin Seok
 * @email seok270@gmail.com
 * @created 2022-04-03
 * @desc
 */

class BaseApiService {
    fun getClient(baseUrl: String): Retrofit? = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(OkHttpClient())
        .addConverterFactory(GsonConverterFactory.create()) .build()
}