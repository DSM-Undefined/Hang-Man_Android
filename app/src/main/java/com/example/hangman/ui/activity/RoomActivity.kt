package com.example.hangman.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.example.hangman.R
import com.example.hangman.contract.RoomContract
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

        btn_ready.setOnClickListener {
            presenter.sendReadyData()
        }
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

    override fun setImageKing(imageView: ImageView) {
        imageView.setImageDrawable(resources.getDrawable(R.drawable.ic_room_king, this.theme))
    }

    override fun setImageMe(imageView: ImageView) {
        imageView.setImageResource(R.drawable.ic_room_me)
    }

    override fun setImageUser(imageView: ImageView) {
        imageView.setImageResource(R.drawable.ic_room_ready_user)
    }

    override fun setImageReadyMe(imageView: ImageView) {
        imageView.setImageResource(R.drawable.ic_room_ready_me)
    }

    override fun setImageReadyUser(imageView: ImageView) {
        imageView.setImageResource(R.drawable.ic_room_ready_user)
    }

    override fun setImageBlock(imageView: ImageView) {
        imageView.setImageResource(R.drawable.ic_room_block)
    }

    override fun setReadyExitEnabled() {
        btn_exit.isEnabled = !btn_exit.isEnabled

        if (btn_ready.isEnabled && btn_exit.isEnabled) {
            btn_ready.setBackgroundColor(ContextCompat.getColor(this, R.color.readyColor))
            btn_exit.setBackgroundColor(ContextCompat.getColor(this, R.color.exitColor))
        } else if (!btn_ready.isEnabled && !btn_exit.isEnabled) {
            btn_ready.setBackgroundColor(ContextCompat.getColor(this, R.color.noReadyColor))
            btn_exit.setBackgroundColor(ContextCompat.getColor(this, R.color.noExitColor))
        }

    }
}
