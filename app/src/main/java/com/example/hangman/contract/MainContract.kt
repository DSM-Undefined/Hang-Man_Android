package com.example.hangman.contract

import com.example.hangman.data.model.Rooms

interface MainContract {
    interface View {
        fun setRoomList(roomList : ArrayList<Rooms>)
        fun errorToast(text: String)
    }

    interface Presenter {
        fun getRoomsData()
    }
}