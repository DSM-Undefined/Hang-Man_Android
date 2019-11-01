package com.example.hangman.ui.activity

import android.content.Intent
import android.graphics.Point
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hangman.contract.MainContract
import com.example.hangman.R
import com.example.hangman.data.model.Room
import com.example.hangman.presenter.MainPresenter
import com.example.hangman.ui.adapter.MainAdapter
import com.example.hangman.ui.dialog.MakeRoomDialog
import com.example.hangman.util.MakeRoomDialogListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainContract.View {
    private var roomList = arrayListOf<Room>()

    private lateinit var presenter: MainPresenter
    private lateinit var adapter: MainAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = MainPresenter(this, this)
        presenter.getRoomsData()

        adapter = MainAdapter(roomList, presenter)
        rv_main.adapter = adapter
        rv_main.layoutManager = LinearLayoutManager(this)

        val makeRoomDialog = MakeRoomDialog(this)

        makeRoomDialog.setListener(object : MakeRoomDialogListener {
            override fun onClickMakeRoom(roomName: String, memberCount: Int) {
                presenter.makeNewRoom(Room(name = roomName, maxPlayer = memberCount))
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

    override fun errorToast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

    override fun setRoomList(roomList: ArrayList<Room>) {
        this.roomList = roomList
        adapter.setList(this.roomList)
    }

    override fun startRoomActivity(roomId: String) {
        val intent = Intent(this, RoomActivity::class.java)
        intent.putExtra("roomId", roomId)
        // 추후 MVP로 제대로된 리팩토링 할때 room id 가져올 때 presenter에서 가져와야 하는가? 아니면 여기서 해도 되는가 등에 대해서 논의한다.
        startActivity(intent)
    }

}
