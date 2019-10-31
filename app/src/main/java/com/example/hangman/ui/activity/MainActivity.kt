package com.example.hangman.ui.activity

import android.content.Intent
import android.graphics.Point
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hangman.contract.MainContract
import com.example.hangman.R
import com.example.hangman.data.model.Participants
import com.example.hangman.data.model.Room
import com.example.hangman.presenter.MainPresenter
import com.example.hangman.ui.adapter.MainAdapter
import com.example.hangman.ui.dialog.MakeRoomDialog
import com.example.hangman.util.MakeRoomDialogListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainContract.View {
    private var roomList = arrayListOf<Room>()
    private lateinit var presenter : MainPresenter
    private lateinit var adapter : MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = MainPresenter(this)

        roomList.add(Room("Sample room1", participants = Participants(1, 3)))
        roomList.add(Room("Sample room2", participants = Participants(2, 4)))
        roomList.add(Room("Sample room3", participants = Participants(3, 4)))
        roomList.add(Room("Sample room4", participants = Participants(4, 6)))
        roomList.add(Room("Sample room5", participants = Participants(1, 6)))
        roomList.add(Room("Sample room6", participants = Participants(3, 6)))
        roomList.add(Room("Sample room7", participants = Participants(4, 6)))
        roomList.add(Room("Sample room8", participants = Participants(3, 6)))

        adapter = MainAdapter(roomList)
        rv_main.adapter = adapter
        rv_main.layoutManager = LinearLayoutManager(this)

        val makeRoomDialog = MakeRoomDialog(this)
        makeRoomDialog.setListener(object : MakeRoomDialogListener {
            override fun onClickMakeRoom(memberCount: Int) {
                val intent = Intent(this@MainActivity, RoomActivity::class.java)
                intent.putExtra("memberCount", memberCount)
                startActivity(intent)
            }
        })

        swipe_main.setOnRefreshListener {
            presenter.getRoomsData()
            swipe_main.isRefreshing = false
        }

        button_make_room_main.setOnClickListener {
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

    override fun setRoomList(roomList : ArrayList<Room>) {
        this.roomList = roomList
        adapter.setList(this.roomList)
    }
}
