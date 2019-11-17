package com.example.hangman.util

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class CreateRetrofit {
    companion object {
        private val baseUrl = "http://hangman.jaehoon.kim/api/v1/"

        private lateinit var retrofit : Retrofit

        fun createRetrofit(): Retrofit {
            retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit
        }
    }
}