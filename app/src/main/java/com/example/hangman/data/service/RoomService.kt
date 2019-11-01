package com.example.hangman.data.service

import com.example.hangman.data.model.Room
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.*

interface RoomService {
    @GET("rooms")
    fun getRoomsData(): Single<ArrayList<Room>>

    @GET("room/{roomId}")
    fun getRoomData(@Path("roomId") roomId: String): Single<Room>

    @POST("room")
    fun newRoomData(@Header("Authorization") token: String, @Body room: Room): Single<Room>

    @PUT("room/{roomId}/participant")
    fun sendReadyData(@Header("Authorization") token: String, @Path("roomId") roomId: String): Single<Response<Void>>

    @DELETE("room/{roomId}/participant")
    fun exitRoom(@Header("Authorization") token: String, @Path("roomId") roomId: String) : Single<Response<Void>>

    @POST("room/{roomId}/participant")
    fun joinRoom(@Header("Authorization") token: String, @Path("roomId") roomId: String): Single<Response<Void>>
}