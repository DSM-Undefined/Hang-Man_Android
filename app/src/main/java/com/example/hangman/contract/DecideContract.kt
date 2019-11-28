package com.example.hangman.contract

interface DecideContract {
    interface View {
        fun appendText(appendText : String)
        fun showToast(text : String)
        fun buttonAnimation()
        fun setEditText(text : String)
        fun finishActivity()
    }

    interface Presenter {
        fun alphabetsOnClick(text : String)
        fun onClickDeleteButton(text : String)
        fun checkLetterCountAndSetAnswer(answer: String, roomId: String)
        fun gameIsEnabled(roomId: String)
    }
}