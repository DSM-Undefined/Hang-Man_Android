package com.example.hangman.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.hangman.R
import com.example.hangman.contract.SigninContract
import com.example.hangman.presenter.SigninPresenter
import kotlinx.android.synthetic.main.activity_signin.*

class SigninActivity : AppCompatActivity(), SigninContract.View {
    private lateinit var presenter : SigninPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)

        presenter = SigninPresenter(this)

        btn_signin.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        tv_signup_button.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }

        /*
            TODO :
             SigninActivity 에서의 Login 은 서버와 통신이 필요한데, 그 이외의 로직은 모두 onClick - startActivity 뿐임.
             우선 MVP 구조를 만들어 두고 추후 서버 개발이 필요한 경우에 MVP가 필요하지 않다고 판단되면 Contract와 Presenter를 제거한다.
         */
    }
}
