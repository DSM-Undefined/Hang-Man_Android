package com.example.hangman.contract

interface SignupContract {
    interface View {
        fun errorToast(text: String)
        fun buttonAnimation()
    }

    interface Presenter {
        fun pwCheckData(pwText : String, pwCheckText : String): Boolean
    }
}