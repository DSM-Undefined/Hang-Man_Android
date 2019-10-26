package com.example.hangman.ui.activity

import android.graphics.Point
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hangman.contract.MainContract
import com.example.hangman.R
import com.example.hangman.data.model.Participants
import com.example.hangman.data.model.Room
import com.example.hangman.ui.adapter.MainAdapter
import com.example.hangman.ui.dialog.MakeRoomDialog
import com.example.hangman.util.MakeRoomDialogListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainContract.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val info: ArrayList<Room> = ArrayList()
        info.add(Room("Sample room1", participants = Participants(1, 3)))
        info.add(Room("Sample room2", participants = Participants(2, 4)))
        info.add(Room("Sample room3", participants = Participants(3, 4)))
        info.add(Room("Sample room4", participants = Participants(4, 6)))
        info.add(Room("Sample room5", participants = Participants(1, 6)))
        info.add(Room("Sample room6", participants = Participants(3, 6)))
        info.add(Room("Sample room7", participants = Participants(4, 6)))
        info.add(Room("Sample room8", participants = Participants(3, 6)))

        val adapter = MainAdapter(info)
        rv_main.adapter = adapter
        rv_main.layoutManager = LinearLayoutManager(applicationContext)

        val makeRoomDialog = MakeRoomDialog(this)
        makeRoomDialog.setListener(object : MakeRoomDialogListener{
            override fun onClickMakeRoom() {
                //TODO: make room Activity
            }

        })
        tv_make_room_main.setOnClickListener{
            makeRoomDialog.show()
            val display = windowManager.defaultDisplay
            val size = Point()
            val window = makeRoomDialog.window
            display.getSize(size)

            val x = (size.x * 0.8f).toInt()
            val y = (size.y * 0.45f).toInt()
            window?.setLayout(x, y)
        }
    }
}
