package com.example.hangman.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import java.io.IOException

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = Intent(this, SigninActivity::class.java)
        startActivity(intent)

        try {
            Thread.sleep(1000)
        } catch (e: IOException) { }

        finish()
    }
}