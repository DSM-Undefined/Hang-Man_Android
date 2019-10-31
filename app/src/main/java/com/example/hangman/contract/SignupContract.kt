package com.example.hangman.contract

import com.example.hangman.data.model.User

interface SignupContract {
    interface View {
        fun errorToast(text: String)
        fun buttonAnimation()
        fun successLoginToast()
        fun failLoginToast(errorReason : String)
    }

    interface Presenter {
        fun pwCheckData(pwText : String, pwCheckText : String): Boolean
        fun doSignUp(user : User)
    }
}