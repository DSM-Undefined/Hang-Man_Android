package com.example.hangman.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import com.example.hangman.R
import com.example.hangman.contract.RoomContract
import com.example.hangman.data.model.Room
import com.example.hangman.presenter.RoomPresenter
import com.example.hangman.util.UserState
import kotlinx.android.synthetic.main.activity_room.*

class RoomActivity : AppCompatActivity(), RoomContract.View {
    private lateinit var presenter: RoomPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room)

        presenter = RoomPresenter(this)
        presenter.getUserData()
        // TODO : getUserData()를 무한반복해야 한다. 그 이유는 방 목록을 갱신해야 되는데 Socket 방식이 아님.
    }

    override fun setImageViews(usersState: ArrayList<UserState>) {
        val imageViewList = arrayListOf<ImageView>(
            iv_character1,
            iv_character2,
            iv_character3,
            iv_character4,
            iv_character5,
            iv_character6
        )

        for (index in imageViewList.indices) {
            when (usersState[index]) {
                UserState.KING -> setImageKing(imageViewList[index])
                UserState.ME -> setImageMe(imageViewList[index])
                UserState.USER -> setImageUser(imageViewList[index])
                UserState.ME_READY -> setImageReadyMe(imageViewList[index])
                UserState.USER_READY -> setImageReadyUser(imageViewList[index])
                UserState.BLOCK -> setImageBlock(imageViewList[index])
            }
        }
    }

    private fun setImageKing(imageView: ImageView) {
        imageView.setImageDrawable(resources.getDrawable(R.drawable.ic_room_king, this.theme))
    }

    private fun setImageMe(imageView: ImageView) {
        imageView.setImageResource(R.drawable.ic_room_me)
    }

    private fun setImageUser(imageView: ImageView) {
        imageView.setImageResource(R.drawable.ic_room_ready_user)
    }

    private fun setImageReadyMe(imageView: ImageView) {
        imageView.setImageResource(R.drawable.ic_room_ready_me)
    }

    private fun setImageReadyUser(imageView: ImageView) {
        imageView.setImageResource(R.drawable.ic_room_ready_user)
    }

    private fun setImageBlock(imageView: ImageView) {
        imageView.setImageResource(R.drawable.ic_room_block)
    }
}
