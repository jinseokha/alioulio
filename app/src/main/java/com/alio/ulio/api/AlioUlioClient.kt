package com.alio.ulio.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @author Ha Jin Seok
 * @email seok270@gmail.com
 * @created 2022-01-26
 * @desc
 */
class AlioUlioClient {

    companion object {
        private const val BASE_URL = "https://dst0smadj7.execute-api.ap-northeast-2.amazonaws.com/"

        fun getApi(): alioApi = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(OkHttpClient())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(alioApi::class.java)

    }
}