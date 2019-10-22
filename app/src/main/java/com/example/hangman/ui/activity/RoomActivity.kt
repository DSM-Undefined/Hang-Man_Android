package com.example.hangman.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.hangman.R

class RoomActivity : AppCompatActivity() {

    /* TODO : RoomActivity는 MainActivity에서 시작된다. MainActivity는 방 목록을 가지고 있다.
        * 고안하고 있는 방법은 MainActivity는 오직 Fragment만 가지고 있는다.
        * 대신 RoomListFragment, RoomReadyFragment -> GameActivity or Fragment 이런 식으로 편한 이동이 가능하다.
        * 즉, MainActivity는 Fragment만을 담는 것으로 로직 변경을 할 생각이다.
        */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room)
    }
}
