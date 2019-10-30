package com.example.hangman.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import com.example.hangman.contract.SignupContract
import com.example.hangman.data.model.User
import com.example.hangman.data.service.AuthService
import com.example.hangman.presenter.SignupPresenter
import com.example.hangman.util.CreateRetrofit
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_decide.*
import kotlinx.android.synthetic.main.activity_signup.*
import android.util.Log
import com.example.hangman.R
import okhttp3.ResponseBody
import retrofit2.Response


class SignupActivity : AppCompatActivity(), SignupContract.View {

    private lateinit var presenter: SignupPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        presenter = SignupPresenter()

        btn_signup.setOnClickListener {
            if (!presenter.pwCheckData(
                    ed_password_signup.text.toString(),
                    ed_pwcheck_signup.text.toString()
                )
            ) {
                showToast("비밀번호가 다릅니다.")
            } else {
                CreateRetrofit.createRetrofit()
                    .create(AuthService::class.java)
                    .userLogin(User("1234", "Test", "real@daum.net"))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(object : DisposableSingleObserver<Response<Void>>() {
                        override fun onSuccess(response: Response<Void>) {
                            Log.d("success", "success")
                        }

                        override fun onError(e: Throwable) {
                            Log.d("error", e.message!!)
                        }
                    })
            }
        }
    }

    override fun errorToast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

    override fun buttonAnimation() {
        val shack: Animation = AnimationUtils.loadAnimation(this, R.anim.dicide_an_error)
        btn_submit.startAnimation(shack)
    }

    private fun showToast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }
}
