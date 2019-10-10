package com.example.hangman.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hangman.Info
import com.example.hangman.contract.MainContract
import com.example.hangman.R
import com.example.hangman.ui.MainAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainContract.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var info: ArrayList<Info> = ArrayList()
        info.add(Info("Sample room1", "1/8"))
        info.add(Info("Sample room2", "3/8"))
        info.add(Info("Sample room3", "6/8"))
        info.add(Info("Sample room4", "7/8"))
        info.add(Info("Sample room5", "1/8"))
        info.add(Info("Sample room6", "2/8"))

        var adapter = MainAdapter(info)
        rv_main.adapter = adapter
        rv_main.layoutManager = LinearLayoutManager(applicationContext)
    }
}
