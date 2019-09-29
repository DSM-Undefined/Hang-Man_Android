package com.example.hangman.contract

interface DecideContract {
    interface View {
        fun appendText(appendText : String)
        fun errorToast(text : String)
        fun buttonAnimation()
        fun setEditText(text : String)
    }

    interface Presenter {
        fun alphabetsOnClick(text : String)
        fun deleteButtonOnClick(text : String)
        fun checkLetterCount(length : Int)
    }
}