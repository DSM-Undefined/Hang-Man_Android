package com.example.hangman.presenter

import android.content.Context
import android.util.Log
import com.example.hangman.contract.MainContract
import com.example.hangman.data.model.Room
import com.example.hangman.data.service.RoomService
import com.example.hangman.util.CreateRetrofit
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import android.content.Context.MODE_PRIVATE

class MainPresenter(private val context: Context, private val view: MainContract.View) :
    MainContract.Presenter {
    override fun getRoomsData() {
        var roomList: ArrayList<Room>
        CreateRetrofit.createRetrofit().create(RoomService::class.java)
            .getRoomsData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableSingleObserver<ArrayList<Room>>() {
                override fun onSuccess(rooms: ArrayList<Room>) {
                    roomList = rooms
                    view.setRoomList(roomList)
                }

                override fun onError(e: Throwable) {
                    e.message?.let { view.errorToast(it) }
                }

            })
    }

    override fun makeNewRoom(room: Room) {
        val pref = context.getSharedPreferences("token", MODE_PRIVATE)
        val token = pref.getString("token", "")

        Log.d("getToken", token)

        CreateRetrofit.createRetrofit().create(RoomService::class.java)
            .newRoomData("Bearer $token", room)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableSingleObserver<Room>() {
                override fun onSuccess(t: Room) {
                    Log.d("roomId", t.id)
                    t.id?.let { view.startRoomActivity(it) }
                }

                override fun onError(e: Throwable) {
                    Log.d("error log", e.message!!)
                }
            })
    }

    override fun joinRoom(roomId: String) {
        val pref = context.getSharedPreferences("token", MODE_PRIVATE)
        val token = pref.getString("token", "")

        Log.d("getToken", token)

        CreateRetrofit.createRetrofit().create(RoomService::class.java)
            .joinRoom("Bearer $token", roomId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableSingleObserver<Response<Void>>() {
                override fun onSuccess(t: Response<Void>) {

                }

                override fun onError(e: Throwable) {
                    Log.d("error log", e.message!!)
                }

            })
    }
}
