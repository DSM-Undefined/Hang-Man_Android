package com.example.hangman.contract

import android.widget.ImageView
import com.example.hangman.util.UserState

interface RoomContract {
    interface View {
        fun setImageViews(usersState: ArrayList<UserState>)

        fun setImageKing(imageView: ImageView)

        fun setImageMe(imageView: ImageView)

        fun setImageUser(imageView: ImageView)

        fun setImageReadyMe(imageView: ImageView)

        fun setImageReadyUser(imageView: ImageView)

        fun setImageBlock(imageView: ImageView)

        fun setReadyExitEnabled()

        fun finishActivity()

        fun setReadyTextChangeStartText()

        fun roomUndefined()
    }

    interface Presenter {
        fun getUserData(roomId : String)

        fun sendReadyData()

        fun sendExitData()
    }
}