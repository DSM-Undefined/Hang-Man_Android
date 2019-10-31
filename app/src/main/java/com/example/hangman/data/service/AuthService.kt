package com.example.hangman.data.service

import com.example.hangman.data.model.Token
import com.example.hangman.data.model.User
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("user/auth")
    fun userSignIn(@Body user : User) : Single<Token>

    @POST("user/signup")
    fun userSignUp(@Body user : User) : Single<Response<Void>>
}
