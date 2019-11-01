package com.example.hangman.ui.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.hangman.R
import com.example.hangman.data.model.Rooms
import com.example.hangman.ui.activity.RoomActivity

class MainAdapter(private var roomList: ArrayList<Rooms>) : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_main, parent, false)
        return MainViewHolder(view)
    }

    override fun getItemCount(): Int = roomList.size

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(roomList[position])
    }

    fun setList(roomList : ArrayList<Rooms>) {
        this.roomList = roomList
        notifyDataSetChanged()
    }

    class MainViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val tvRoomName: TextView = itemView.findViewById(R.id.tv_room_name_main_item)
        private val tvCount: TextView = itemView.findViewById(R.id.tv_count_main_item)
        private val itemLayout : ConstraintLayout = itemView.findViewById(R.id.layout_item_main)

        fun bind(room : Rooms) {
            tvRoomName.text = room.name
            tvCount.text = "${room.participants?.size}/${room.maxPlayer}"
            itemLayout.setOnClickListener {
                itemView.context.startActivity(Intent(itemView.context, RoomActivity::class.java))
                /* TODO : 방에 대한 데이터 Intent를 통해서 전달하는데
                 ** 방식 1. ID값을 넘기고 Room 내부에서 CRUD로 확인한다.
                 ** 방식 2, 여기서 검색하고 방 데이터를 넘긴다. */
            }
        }
    }
}