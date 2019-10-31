package com.example.hangman.contract

interface SigninContract {
    interface View {
        fun startMainActivity()
        fun showToast(text : String)
    }

    interface Presenter {
        fun doSignIn(id : String, password : String)
    }
}