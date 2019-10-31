package com.example.hangman.contract

import com.example.hangman.data.model.Room

interface MainContract {
    interface View {
        fun setRoomList(roomList : ArrayList<Room>)
        fun errorToast(text: String)
    }

    interface Presenter {
        fun getRoomsData()
        fun makeNewRoom(room : Room)
        fun joinRoom(roomId : String)
    }
}