package com.example.hangman.presenter

import com.example.hangman.contract.RoomContract
import com.example.hangman.util.UserState

class RoomPresenter(private val view: RoomContract.View) : RoomContract.Presenter {
    override fun getUserData() {
        // TODO : 이 부분에서 서버와 통신을 통해서 방 유저들의 목록과 상태를 가져온다.
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
        // TODO : 내 Ready 여부를 전송합니다. 또한 유저 정보를 여기서 '한번 더' 가져오면서 자신의 레디 정보를 갱신합니다.
        view.setReadyExitEnabled()
        getUserData()
    }

    override fun sendExitData() {
        // TODO : 방을 나갈 경우 이 메소드에서 방 나감을 서버에 통보하는 내용 작성합니다.
    }
}