package com.example.hangman.contract

import com.example.hangman.data.model.Room

interface MainContract {
    interface View {
        fun setRoomList(roomList : ArrayList<Room>)
    }

    interface Presenter {
        fun getRoomsData()
    }
}