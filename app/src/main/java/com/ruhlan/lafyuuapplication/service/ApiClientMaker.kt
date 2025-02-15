package com.ruhlan.lafyuuapplication.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by r.usubov on 15.02.25.
 */
class ApiClientMaker {
    companion object {
        val BASE_URL = "https://dummyjson.com"
        val retrofit =
            Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
                .build()
        val api = retrofit.create(LafyuuService::class.java)
    }
}