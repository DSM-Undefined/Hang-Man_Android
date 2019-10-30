package com.example.hangman.contract

interface GameroomContract {
    interface View {
        fun appendText(appendText : String)
        fun rightText(appendText: String, index: Int)
        fun setEditText(text: String)
        fun wrongText(appendText: String)
        fun gameFail()
        fun gameSuccess()
    }

    interface Presenter {
        fun alphabetsOnClick(text : String)
    }
}