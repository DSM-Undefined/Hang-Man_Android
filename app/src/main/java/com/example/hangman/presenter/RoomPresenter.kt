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

    override fun sendReadyData() {
        // TODO : isReady 등, 게임 방 안에서 User의 Ready 여부 등에 대한 서버 통신 내용을 작성합니다.
        view.setReadyExitEnabled()
    }

    override fun sendExitData() {
        // TODO : 방을 나갈 경우 이 메소드에서 방 나감을 서버에 통보하는 내용 작성합니다.
    }
}