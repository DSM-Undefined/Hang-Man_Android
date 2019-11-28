package com.example.hangman.presenter

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.util.Log
import com.example.hangman.contract.SigninContract
import com.example.hangman.data.model.Token
import com.example.hangman.data.model.User
import com.example.hangman.data.service.AuthService
import com.example.hangman.util.CreateRetrofit
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class SigninPresenter(private val context : Context, private val view : SigninContract.View) : SigninContract.Presenter {
    override fun doSignIn(id: String, password: String) {
        val user = User(id = id, password = password)

        CreateRetrofit.createRetrofit().create(AuthService::class.java)
            .userSignIn(user)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableSingleObserver<Token>() {
                override fun onSuccess(t: Token) {
                    val pref = context.getSharedPreferences("token", MODE_PRIVATE)
                    val editor = pref.edit()
                    Log.d("save token", t.access)
                    editor.putString("token", t.access)
                    editor.putString("id", id)
                    editor.apply()

                    view.startMainActivity()
                }

                override fun onError(e: Throwable) {
                    e.message?.let { view.showToast(it) }
                }
            })
    }
}