package com.example.hangman.presenter

import android.content.Context
import android.util.Log
import com.example.hangman.contract.DecideContract
import com.example.hangman.data.model.Answer
import com.example.hangman.data.service.GameService
import com.example.hangman.util.CreateRetrofit
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import okhttp3.ResponseBody
import retrofit2.Response

class DecidePresenter(private val view: DecideContract.View, private val context: Context) :
    DecideContract.Presenter {
    override fun alphabetsOnClick(text: String) {
        view.appendText(text)
    }

    override fun onClickDeleteButton(text: String) {
        if (text.isNotEmpty()) {
            view.setEditText(text.substring(0, text.length - 1))
        }
    }

    override fun checkLetterCountAndSetAnswer(answer: String, roomId: String) {
        when (answer.length) {
            in 2..10 -> {
                val pref = context.getSharedPreferences("token", Context.MODE_PRIVATE)
                val token: String? = pref.getString("token", "")

                CreateRetrofit.createRetrofit().create(GameService::class.java)
                    .setAnswer("Bearer $token", roomId, Answer(answer))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(object : DisposableSingleObserver<Response<Void>>() {
                        override fun onSuccess(t: Response<Void>) {
                            Log.e("error handling", t.code().toString())
                            view.showToast("단어 설정이 완료되었습니다.")
                        }

                        override fun onError(e: Throwable) {
                            Log.e("error handling", e.message)
                        }
                    })

            }
            0, 1 -> {
                view.showToast("설정할 단어는 2 ~ 10글자이어야 합니다.")
                view.buttonAnimation()
            }
        }
    }
}