package com.example.hangman.contract

interface SigninContract {
    interface View {
        fun startMainActivity()
    }

    interface Presenter {
        fun checkIDPW(id : String, password : String)
    }
}