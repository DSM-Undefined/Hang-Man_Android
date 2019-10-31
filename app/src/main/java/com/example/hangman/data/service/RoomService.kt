package com.example.hangman.data.service

import com.example.hangman.data.model.Rooms
import io.reactivex.Single
import retrofit2.http.GET

interface RoomService {
    @GET("rooms")
    fun getRoomsData() : Single<ArrayList<Rooms>>
}