package com.example.hangman.presenter

import android.content.Context
import android.util.Log
import com.example.hangman.contract.GameroomContract
import com.example.hangman.data.model.Answer
import com.example.hangman.data.model.Index
import com.example.hangman.data.model.Room
import com.example.hangman.data.service.GameService
import com.example.hangman.data.service.RoomService
import com.example.hangman.ui.activity.GameroomActivity
import com.example.hangman.util.CreateRetrofit
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import java.lang.NullPointerException

class GameroomPresenter(private val view: GameroomActivity, private val context: Context) :
    GameroomContract.Presenter {
    private var roomId : String? = null

    override fun getWordLength(roomId: String) {
        this.roomId = roomId

        CreateRetrofit.createRetrofit().create(RoomService::class.java)
            .getRoomData(roomId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableSingleObserver<Room>() {
                override fun onSuccess(t: Room) {
                    Log.d("gameroompresen length", t.word?.length.toString())
                    t.word?.length?.let { view.setLength(it) }
                }

                override fun onError(e: Throwable) {}

            })
    }

    override fun alphabetsOnClick(text: String, roomId: String) {
        this.roomId = roomId

        val pref = context.getSharedPreferences("token", Context.MODE_PRIVATE)
        val token: String? = pref.getString("token", "")

        CreateRetrofit.createRetrofit().create(GameService::class.java)
            .sendAnswer("Bearer $token", roomId, Answer(text))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableSingleObserver<Index>() {
                override fun onSuccess(t: Index) {
                    for (index in t.index!!) {
                        index?.let { view.rightText(text, it) }
                    }
                }

                override fun onError(e: Throwable) {
                    if(e.message == null) {
                        view.finishGame()
                    } else {
                        view.wrongText(text)
                    }
                }

            })
    }

    override fun sendExitData() {
        val pref = context.getSharedPreferences("token", Context.MODE_PRIVATE)
        val token: String? = pref.getString("token", "")

        token?.let {
            roomId?.let { it1 ->
                CreateRetrofit.createRetrofit().create(RoomService::class.java)
                    .exitRoom("Bearer $it", it1)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(object : DisposableSingleObserver<Response<Void>>() {
                        override fun onSuccess(t: Response<Void>) {
                            view.finishGameroomActivity()
                        }

                        override fun onError(e: Throwable) {
                            Log.d("error", e.message!!)
                        }
                    })
            }
        }
    }
}