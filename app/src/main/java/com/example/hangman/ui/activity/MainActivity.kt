package com.example.hangman.ui.activity

import android.content.Intent
import android.graphics.Point
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hangman.contract.MainContract
import com.example.hangman.R
import com.example.hangman.data.model.Rooms
import com.example.hangman.presenter.MainPresenter
import com.example.hangman.ui.adapter.MainAdapter
import com.example.hangman.ui.dialog.MakeRoomDialog
import com.example.hangman.util.MakeRoomDialogListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainContract.View {
    private var roomList = arrayListOf<Rooms>()

    private lateinit var presenter: MainPresenter
    private lateinit var adapter: MainAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = MainPresenter(this)
        presenter.getRoomsData()

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

        tv_make_room_main.setOnClickListener {
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

    override fun setRoomList(roomList: ArrayList<Rooms>) {
        this.roomList = roomList
        adapter.setList(this.roomList)
    }
}
