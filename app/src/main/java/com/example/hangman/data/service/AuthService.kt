package com.example.hangman.data.service

import com.example.hangman.data.model.Token
import com.example.hangman.data.model.User
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface AuthService {
    @POST("user/auth")
    fun userSignIn(@Body user : User) : Single<Token>

    @GET("user/{id}")
    fun getUserData(@Path("id") id : String) : Single<User>

    @POST("user/signup")
    fun userSignUp(@Body user : User) : Single<Response<Void>>
}
