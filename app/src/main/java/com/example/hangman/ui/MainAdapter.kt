package com.example.hangman.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hangman.Info
import com.example.hangman.R

class MainAdapter(info: ArrayList<Info>) : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    var info: ArrayList<Info> = info

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_main, parent, false)
        return MainViewHolder(view)
    }

    override fun getItemCount(): Int {
        return info.size
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        var room = info[position]
        holder.roomName.text = room.roomTitle
        holder.count.text = room.count
    }

    class MainViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var roomName: TextView = itemView.findViewById(R.id.tv_room_name_main_item)
        var count: TextView = itemView.findViewById(R.id.tv_count_main_item)
    }
}