package com.example.hangman.presenter

import com.example.hangman.contract.MainContract
import com.example.hangman.data.model.Participants
import com.example.hangman.data.model.Room

class MainPresenter(private val view: MainContract.View) : MainContract.Presenter {
    override fun getRoomsData() {
        val roomList = arrayListOf<Room>()
        roomList.add(Room("Refresh room1", participants = Participants(1, 3)))
        roomList.add(Room("Refresh room2", participants = Participants(2, 4)))
        roomList.add(Room("Refresh room3", participants = Participants(3, 4)))
        roomList.add(Room("Refresh room4", participants = Participants(4, 6)))
        roomList.add(Room("Refresh room5", participants = Participants(1, 6)))
        roomList.add(Room("Refresh room6", participants = Participants(3, 6)))
        roomList.add(Room("Refresh room7", participants = Participants(4, 6)))
        roomList.add(Room("Refresh room8", participants = Participants(3, 6)))
        // TODO : 여기에서 방 리스트를 가져오는 로직을 작성
        view.setRoomList(roomList)
    }

}