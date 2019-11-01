package com.example.hangman.presenter

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.hangman.contract.RoomContract
import com.example.hangman.data.model.Room
import com.example.hangman.data.model.User
import com.example.hangman.data.service.AuthService
import com.example.hangman.data.service.RoomService
import com.example.hangman.util.CreateRetrofit
import com.example.hangman.util.UserState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

class RoomPresenter(private val view: RoomContract.View, private val context: Context) :
    RoomContract.Presenter {
    private lateinit var roomId: String

    override fun getUserData(roomId: String) {
        val pref = context.getSharedPreferences("token", Context.MODE_PRIVATE)
        this.roomId = roomId
        val myId: String? = pref.getString("id", "")

        CreateRetrofit.createRetrofit().create(RoomService::class.java)
            .getRoomData(roomId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableSingleObserver<Room>() {
                override fun onSuccess(room: Room) {
                    val users = room.participants
                    val max = room.maxPlayer

                    val userImageData = ArrayList<UserState>()
                    max?.let {
                        repeat(it) { userImageData.add(UserState.BLOCK) }
                    }

                    Log.d("users data", users.toString())
                    Log.d("admin data", room.admin)
                    Log.d("data 비교", (room.admin == users!![0]).toString())

                    for (index in room.participants.indices) {
                        val userId = room.participants[index]
                        CreateRetrofit.createRetrofit().create(AuthService::class.java)
                            .getUserData(userId!!)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(object : DisposableSingleObserver<User>() {
                                override fun onSuccess(t: User) {
                                    if (userId == room.admin) {
                                        userImageData[index] = UserState.KING
                                        if(userId == myId) {
                                            view.setReadyTextChangeStartText()
                                        }
                                    } else if (userId == myId && t.ready == true) {
                                        userImageData[index] = UserState.ME_READY
                                    } else if (userId == myId && t.ready == false) {
                                        userImageData[index] = UserState.ME
                                    } else if (userId != myId && t.ready == true) {
                                        userImageData[index] = UserState.USER_READY
                                    } else if (userId != myId && t.ready == false) {
                                        userImageData[index] = UserState.USER
                                    } else {
                                        userImageData[index] = UserState.BLOCK
                                    }

                                    view.setImageViews(userImageData)
                                }

                                override fun onError(e: Throwable) {
                                    Log.e("error!", e.message!!)
                                }
                            })
                    }
                }

                override fun onError(e: Throwable) {
                    if(e.message == "HTTP 404 Not Found") {
                        view.roomUndefined()
                    }
                }

            })
    }

    override fun sendReadyData() {
        val pref = context.getSharedPreferences("token", Context.MODE_PRIVATE)
        val token: String? = pref.getString("token", "")

        CreateRetrofit.createRetrofit().create(RoomService::class.java)
            .sendReadyData("Bearer $token", roomId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableSingleObserver<Response<Void>>() {
                override fun onSuccess(t: Response<Void>) {
                    view.setReadyExitEnabled()
                    getUserData(roomId)
                }

                override fun onError(e: Throwable) {
                    Log.d("error log", e.message!!)
                }

            })
    }

    override fun sendExitData() {
        val pref = context.getSharedPreferences("token", Context.MODE_PRIVATE)
        val token: String? = pref.getString("token", "")

        token?.let {
            CreateRetrofit.createRetrofit().create(RoomService::class.java)
                .exitRoom("Bearer $it", roomId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : DisposableSingleObserver<Response<Void>>() {
                    override fun onSuccess(t: Response<Void>) {
                        view.finishActivity()
                    }

                    override fun onError(e: Throwable) {
                        Log.d("error", e.message!!)
                    }
                })
        }
    }
}