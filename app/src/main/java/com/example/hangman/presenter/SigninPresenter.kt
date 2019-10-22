package com.example.hangman.presenter

import com.example.hangman.contract.SigninContract

class SigninPresenter(private val view : SigninContract.View) : SigninContract.Presenter {
    override fun checkIDPW(id: String, password: String) {
        // TODO : ID와 PW 확인하는 서버 통신 로직 작성
        view.startMainActivity()
    }
}