package com.example.hangman.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import com.example.hangman.contract.SignupContract
import com.example.hangman.data.model.User
import com.example.hangman.presenter.SignupPresenter
import kotlinx.android.synthetic.main.activity_decide.*
import kotlinx.android.synthetic.main.activity_signup.*
import com.example.hangman.R


class SignupActivity : AppCompatActivity(), SignupContract.View {

    private lateinit var presenter: SignupPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        presenter = SignupPresenter(this)

        btn_signup.setOnClickListener {
            if (ed_id_signup.text.toString().isEmpty()) {
                showToast("아이디를 입력하세요.")
            } else if (ed_password_signup.text.toString().isEmpty()) {
                showToast("비밀번호를 입력하세요.")
            } else if (ed_pwcheck_signup.text.toString().isEmpty()) {
                showToast("비밀번호 확인을 입력하세요.")
            } else if (!presenter.pwCheckData(
                    ed_password_signup.text.toString(),
                    ed_pwcheck_signup.text.toString()
                )
            ) {
                showToast("비밀번호가 다릅니다.")
            } else {
                presenter.doSignUp(
                    User(
                        id = ed_id_signup.text.toString(),
                        password = ed_password_signup.text.toString()
                    )
                )
            }
        }
    }

    override fun successLoginToast() {
        showToast("회원가입에 성공했습니다.")
        finish()
    }

    override fun failLoginToast(errorReason: String) {
        showToast(errorReason)
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
