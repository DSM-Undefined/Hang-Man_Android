package com.example.hangman.contract

interface GameroomContract {
    interface View {

    }

    interface Presenter {
        fun alphabetsOnClick(text: String)
    }
}