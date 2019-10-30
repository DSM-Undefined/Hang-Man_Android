package com.example.hangman.presenter

import android.util.Log
import com.example.hangman.contract.SignupContract
import com.example.hangman.data.model.User
import com.example.hangman.data.service.AuthService
import com.example.hangman.util.CreateRetrofit
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

class SignupPresenter(val view : SignupContract.View) : SignupContract.Presenter {

    override fun pwCheckData(pwText: String, pwCheckText: String): Boolean {
        if (pwText.isEmpty() || pwCheckText.isEmpty()) {
            return false
        } else {
            return pwText == pwCheckText
        }
    }

    override fun doLogin(user: User) {
        CreateRetrofit.createRetrofit()
            .create(AuthService::class.java)
            .userLogin(user)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableSingleObserver<Response<Void>>() {
                override fun onSuccess(response: Response<Void>) {
                    view.successLoginToast()
                }

                override fun onError(e: Throwable) {
                    e.message?.let { view.errorToast(it) }
                }
            })
    }
}