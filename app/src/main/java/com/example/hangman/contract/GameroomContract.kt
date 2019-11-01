package com.example.hangman.contract

interface GameroomContract {
    interface View {
        fun rightText(appendText: String, index: Int)
        fun setEditText(text: String)
        fun wrongText(appendText: String)
        fun gameFail()
        fun gameSuccess()
        fun setLength(length: Int)
        fun finishGame()
    }

    interface Presenter {
        fun alphabetsOnClick(text: String, roomId: String)
        fun getWordLength(roomId: String)
    }
}