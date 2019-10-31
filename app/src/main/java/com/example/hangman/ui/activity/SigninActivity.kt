package com.example.hangman.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.hangman.R
import com.example.hangman.contract.SigninContract
import com.example.hangman.presenter.SigninPresenter
import kotlinx.android.synthetic.main.activity_signin.*

class SigninActivity : AppCompatActivity(), SigninContract.View {
    private lateinit var presenter: SigninPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)

        presenter = SigninPresenter(this, this)

        btn_signin.setOnClickListener {
            presenter.doSignIn(ed_id.text.toString(), ed_password.text.toString())
            finish()
        }

        tv_signup_button.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }
    }

    override fun startMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
    }

    override fun showToast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }
}
