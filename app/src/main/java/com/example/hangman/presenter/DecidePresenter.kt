package com.example.hangman.presenter

import com.example.hangman.contract.DecideContract

class DecidePresenter(private val view: DecideContract.View) : DecideContract.Presenter {
    override fun alphabetsOnClick(text: String) {
        view.appendText(text)
    }

    override fun onClickDeleteButton(text: String) {
        if(text.isNotEmpty()) {
            view.setEditText(text.substring(0, text.length - 1))
        }
    }

    override fun checkLetterCount(length: Int) {
        when (length) {
            in 2..10 -> view.errorToast("단어 설정이 완료되었습니다.")
            0, 1 -> {
                view.errorToast("설정할 단어는 2 ~ 10글자이어야 합니다.")
                view.buttonAnimation()
            }
        }
    }
}