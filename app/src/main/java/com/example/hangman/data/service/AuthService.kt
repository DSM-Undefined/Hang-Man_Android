package com.example.hangman.data.service

import com.example.hangman.data.model.User
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("user/signup")
    fun userLogin(@Body user : User) : Single<Response<Void>>
}