package com.example.reddit.data.retrofit

import com.example.reddit.utils.BASE_URL
import com.example.reddit.data.api.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitReddit {
    companion object {

        private var retrofit: ApiService? = null

        fun getApiServiceRetrofit(): ApiService {
            when (retrofit) {
                null -> retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(
                        GsonConverterFactory.create()
                    )
                    .build().create(ApiService::class.java)
            }
            return retrofit as ApiService
        }
    }
}