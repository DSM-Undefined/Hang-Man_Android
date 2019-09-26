package com.example.hangman.contract

interface DecideContract {
    interface View {
        fun appendText(appendText : String)
    }

    interface Presenter {
        fun alphabetsOnClick(data : String)
    }
}