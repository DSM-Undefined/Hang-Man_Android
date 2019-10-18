package com.example.hangman.presenter

import com.example.hangman.contract.SignupContract

class SignupPresenter : SignupContract.Presenter {
    override fun pwCheckData(pwText: String, pwCheckText: String): Boolean {
        if (pwText.isEmpty() || pwCheckText.isEmpty()) {
            return false
        } else {
            return pwText == pwCheckText
        }
    }
}