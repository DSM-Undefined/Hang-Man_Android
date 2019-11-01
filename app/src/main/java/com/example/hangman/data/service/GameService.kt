package com.example.hangman.data.service

import com.example.hangman.data.model.Answer
import com.example.hangman.data.model.Index
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.*

interface GameService {
    @PUT("room/{room_id}")
    fun setAnswer(@Header("Authorization") token: String, @Path("room_id") roomId: String, @Body answer: Answer): Single<Response<Void>>

    @PATCH("room/{room_id}/answer")
    fun sendAnswer(@Header("Authorization") token: String, @Path("room_id") roomId: String, @Body answer: Answer) : Single<Index>
}