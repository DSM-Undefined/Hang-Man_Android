package com.example.hangman.presenter

import com.example.hangman.contract.DecideContract

class DecidePresenter(private val view : DecideContract.View) : DecideContract.Presenter {
    override fun alphabetsOnClick(data : String) {
        view.appendText(data)
    }
}