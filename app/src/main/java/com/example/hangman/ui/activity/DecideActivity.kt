package com.example.hangman.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.hangman.R
import kotlinx.android.synthetic.main.activity_decide.*

class DecideActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_decide)

        tv_kb_a.setOnClickListener(){
            tv_kb_a.setText("dp")
        }
    }
}
