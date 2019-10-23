package com.example.hangman.contract

import com.example.hangman.util.UserState

interface RoomContract {
    interface View {
        fun setImageViews(usersState : ArrayList<UserState>)
    }

    interface Presenter {
        fun getUserData()
    }
}