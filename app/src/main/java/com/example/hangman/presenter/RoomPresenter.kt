package com.example.hangman.presenter

import com.example.hangman.contract.RoomContract
import com.example.hangman.util.UserState

class RoomPresenter(private val view: RoomContract.View) : RoomContract.Presenter {
    override fun getUserData() {
        view.setImageViews(
            arrayListOf(
                UserState.KING,
                UserState.ME,
                UserState.USER_READY,
                UserState.USER,
                UserState.BLOCK,
                UserState.BLOCK
            )
        )
    }
}