package com.example.hangman.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.hangman.R
import com.example.hangman.contract.RoomContract
import com.example.hangman.presenter.RoomPresenter
import com.example.hangman.util.UserState
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_room.*
import java.util.concurrent.TimeUnit
import kotlin.collections.ArrayList

class RoomActivity : AppCompatActivity(), RoomContract.View {
    private lateinit var presenter: RoomPresenter
    private var repeatRoomDisposable: Disposable? = null
    private var isReady: Boolean = false
    private var roomId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room)

        roomId = intent.extras?.getString("roomId")

        Log.d("getRoomID", roomId)

        presenter = RoomPresenter(this, this)

        btn_ready.setOnClickListener {
            if (btn_ready.text == "시작") {
                val intent = Intent(this, DecideActivity::class.java)
                intent.putExtra("roomId", roomId)
                startActivity(intent)
            } else {
                isReady = !isReady
                if (isReady) {
                    btn_ready.text = "준비완료"
                } else {
                    btn_ready.text = "준비"
                }
                presenter.sendReadyData()
            }
        }

        btn_exit.setOnClickListener {
            presenter.sendExitData()
            finish()
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

        for (index in usersState.indices) {
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
        imageView.setImageResource(R.drawable.ic_room_user)
    }

    override fun setImageReadyMe(imageView: ImageView) {
        imageView.setImageResource(R.drawable.ic_room_ready_me)
    }

    override fun setImageReadyUser(imageView: ImageView) {
        imageView.setImageResource(R.drawable.ic_room_ready_user)
    }

    override fun setImageBlock(imageView: ImageView) {
        imageView.setImageResource(0)
    }

    override fun setReadyTextChangeStartText() {
        btn_ready.text = "시작"
    }

    override fun setReadyExitEnabled() {
        btn_exit.isEnabled = !btn_exit.isEnabled

        setBackgroundColor()
    }

    override fun finishActivity() {
        finish()
    }

    override fun onBackPressed() {
        Toast.makeText(this, "나가기 버튼을 이용해 주세요.", Toast.LENGTH_SHORT).show()
    }

    override fun roomUndefined() {
        Toast.makeText(this, "방장이 게임에서 나갔습니다.", Toast.LENGTH_SHORT).show()
        finish()
    }

    override fun startGameroomActivity() {
        val intent = Intent(this, GameroomActivity::class.java)
        intent.putExtra("roomId", roomId)
        startActivity(intent)
    }

    private fun setBackgroundColor() {
        if (btn_exit.isEnabled) {
            btn_ready.setBackgroundColor(ContextCompat.getColor(this, R.color.colorAccent))
            btn_exit.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary))
        } else if (!btn_exit.isEnabled) {
            btn_ready.setBackgroundColor(ContextCompat.getColor(this, R.color.readyButton))
            btn_exit.setBackgroundColor(ContextCompat.getColor(this, R.color.readyOutButton))
        }
    }

    override fun onResume() {
        super.onResume()
        repeatRoomDisposable = roomId?.let { roomId ->
            Observable
                .interval(1, TimeUnit.SECONDS)
                .subscribe { presenter.getUserData(roomId) }
        }
    }

    override fun onStop() {
        super.onStop()
        repeatRoomDisposable?.dispose()
    }

    override fun onDestroy() {
        super.onDestroy()
        repeatRoomDisposable?.dispose()
    }
}
