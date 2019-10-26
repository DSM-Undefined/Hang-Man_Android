package com.example.hangman.util

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CreateRetrofit {
    companion object {
        private val baseUrl = "http://hangmanapi.jaehoon.kim:8083/api/v1"

        private val retrofit =
            Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        fun <C> createRetrofit(clazz: Class<C>): C {
            return retrofit.create(clazz)
        }
    }
}