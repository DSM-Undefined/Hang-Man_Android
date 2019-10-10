package com.example.hangman.contract

interface SignupContract {
    interface View {
        fun errorToast(text: String)
        fun buttonAnimation()
    }

    interface Presenter {
        fun checkRegisterData(vararg text : String)
    }
}