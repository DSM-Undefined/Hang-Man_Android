package com.example.hangman.presenter

import com.example.hangman.contract.MainContract
import com.example.hangman.data.model.Rooms
import com.example.hangman.data.service.RoomService
import com.example.hangman.util.CreateRetrofit
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class MainPresenter(private val view: MainContract.View) : MainContract.Presenter {
    override fun getRoomsData() {
        var roomList: ArrayList<Rooms>
        CreateRetrofit.createRetrofit().create(RoomService::class.java)
            .getRoomsData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableSingleObserver<ArrayList<Rooms>>() {
                override fun onSuccess(rooms: ArrayList<Rooms>) {
                    roomList = rooms
                    view.setRoomList(roomList)
                }

                override fun onError(e: Throwable) {
                    e.message?.let { view.errorToast(it) }
                }

            })
    }

}