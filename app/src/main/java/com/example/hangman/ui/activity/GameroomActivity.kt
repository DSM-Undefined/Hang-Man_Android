package com.example.hangman.ui.activity

import android.graphics.Color
import android.graphics.drawable.Animatable
import android.graphics.drawable.AnimatedVectorDrawable
import android.graphics.drawable.AnimationDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.*
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat
import com.example.hangman.R
import com.example.hangman.contract.GameroomContract
import com.example.hangman.presenter.GameroomPresenter
import kotlinx.android.synthetic.main.activity_decide.*
import kotlinx.android.synthetic.main.activity_decide.tv_kb_a
import kotlinx.android.synthetic.main.activity_decide.tv_kb_b
import kotlinx.android.synthetic.main.activity_decide.tv_kb_c
import kotlinx.android.synthetic.main.activity_decide.tv_kb_d
import kotlinx.android.synthetic.main.activity_decide.tv_kb_e
import kotlinx.android.synthetic.main.activity_decide.tv_kb_f
import kotlinx.android.synthetic.main.activity_decide.tv_kb_g
import kotlinx.android.synthetic.main.activity_decide.tv_kb_h
import kotlinx.android.synthetic.main.activity_decide.tv_kb_i
import kotlinx.android.synthetic.main.activity_decide.tv_kb_j
import kotlinx.android.synthetic.main.activity_decide.tv_kb_k
import kotlinx.android.synthetic.main.activity_decide.tv_kb_l
import kotlinx.android.synthetic.main.activity_decide.tv_kb_m
import kotlinx.android.synthetic.main.activity_decide.tv_kb_n
import kotlinx.android.synthetic.main.activity_decide.tv_kb_o
import kotlinx.android.synthetic.main.activity_decide.tv_kb_p
import kotlinx.android.synthetic.main.activity_decide.tv_kb_q
import kotlinx.android.synthetic.main.activity_decide.tv_kb_r
import kotlinx.android.synthetic.main.activity_decide.tv_kb_s
import kotlinx.android.synthetic.main.activity_decide.tv_kb_t
import kotlinx.android.synthetic.main.activity_decide.tv_kb_u
import kotlinx.android.synthetic.main.activity_decide.tv_kb_v
import kotlinx.android.synthetic.main.activity_decide.tv_kb_w
import kotlinx.android.synthetic.main.activity_decide.tv_kb_x
import kotlinx.android.synthetic.main.activity_decide.tv_kb_y
import kotlinx.android.synthetic.main.activity_decide.tv_kb_z
import kotlinx.android.synthetic.main.activity_gameroom.*

class GameroomActivity : AppCompatActivity(), View.OnClickListener, GameroomContract.View {

    var tvArray = arrayListOf<TextView>()

    private lateinit var presenter: GameroomPresenter
    private var roomId: String? = null
    private var length: Int? = null
    private var deathCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gameroom)

        roomId = intent.extras?.getString("roomId")
        presenter = GameroomPresenter(this, this)
        initViewListener()

        presenter.getWordLength(roomId!!)

        Log.d("gameroomactivity length", length.toString())
    }

    private fun addEditTexts(num: Int) {
        val tvcolor = "#2B2A26"
        val linearlayout: LinearLayout = findViewById(R.id.gameroom_letter)
        linearlayout.gravity = Gravity.CENTER

        tvArray = arrayListOf()
        length?.plus(1)?.let {
            repeat(it) {
                tvArray.add(TextView(this))
            }
        }
        val tvGameroom1 = TextView(this)
        tvGameroom1.text = "  "
        tvGameroom1.textSize = 35F
        for (i in 0..num) {
            tvArray[i].setTextColor(Color.parseColor(tvcolor))
            tvArray[i].setPadding(0, 0, 0, 3)
            tvArray[i].setTextSize(TypedValue.COMPLEX_UNIT_DIP, 35F)
            tvArray[i].gravity = Gravity.CENTER
            tvArray[i].setBackgroundResource(R.drawable.gameroom_edittext_custum)
        }
        for (i in 0..num) {
            linearlayout.addView(tvArray[i])
        }

    }


    private fun initViewListener() {
        tv_kb_a.setOnClickListener(this)
        tv_kb_b.setOnClickListener(this)
        tv_kb_c.setOnClickListener(this)
        tv_kb_d.setOnClickListener(this)
        tv_kb_e.setOnClickListener(this)
        tv_kb_f.setOnClickListener(this)
        tv_kb_g.setOnClickListener(this)
        tv_kb_h.setOnClickListener(this)
        tv_kb_i.setOnClickListener(this)
        tv_kb_j.setOnClickListener(this)
        tv_kb_k.setOnClickListener(this)
        tv_kb_l.setOnClickListener(this)
        tv_kb_m.setOnClickListener(this)
        tv_kb_n.setOnClickListener(this)
        tv_kb_o.setOnClickListener(this)
        tv_kb_p.setOnClickListener(this)
        tv_kb_q.setOnClickListener(this)
        tv_kb_r.setOnClickListener(this)
        tv_kb_s.setOnClickListener(this)
        tv_kb_t.setOnClickListener(this)
        tv_kb_u.setOnClickListener(this)
        tv_kb_v.setOnClickListener(this)
        tv_kb_w.setOnClickListener(this)
        tv_kb_x.setOnClickListener(this)
        tv_kb_y.setOnClickListener(this)
        tv_kb_z.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        if (view is TextView?) {
            roomId?.let { presenter.alphabetsOnClick(view?.text.toString(), it) }
        }
    }

    override fun setEditText(text: String) {
        ed_Questions_word.setText(text)
    }

    override fun wrongText(appendText: String) {
        val tvid = resources.getIdentifier("tv_kb_" + appendText.toLowerCase(), "id", packageName)
        val tv: TextView = findViewById(tvid)
        tv.setBackgroundResource(com.example.hangman.R.drawable.ic_close_black_24dp)
        tv.isEnabled = false

        when (deathCount) {
            0 -> {
                val drawable = iv_hangman.drawable
                if(drawable is AnimatedVectorDrawable) {
                    val animatedVectorDrawable = drawable as AnimatedVectorDrawable
                    animatedVectorDrawable.start()
                } else if (drawable is AnimatedVectorDrawableCompat) {
                    val animatedVectorDrawableCompat = drawable as AnimatedVectorDrawableCompat
                    animatedVectorDrawableCompat.start()
                }
                iv_hangman.setImageResource(R.drawable.game_hang_man_head)
            }
            1 -> {
                val drawable = iv_hangman.drawable
                if(drawable is AnimatedVectorDrawable) {
                    val animatedVectorDrawable = drawable as AnimatedVectorDrawable
                    animatedVectorDrawable.start()
                } else if (drawable is AnimatedVectorDrawableCompat) {
                    val animatedVectorDrawableCompat = drawable as AnimatedVectorDrawableCompat
                    animatedVectorDrawableCompat.start()
                }
                iv_hangman.setImageResource(R.drawable.game_hang_man_body)
            }
            2 -> {
                val drawable = iv_hangman.drawable
                if(drawable is AnimatedVectorDrawable) {
                    val animatedVectorDrawable = drawable as AnimatedVectorDrawable
                    animatedVectorDrawable.start()
                } else if (drawable is AnimatedVectorDrawableCompat) {
                    val animatedVectorDrawableCompat = drawable as AnimatedVectorDrawableCompat
                    animatedVectorDrawableCompat.start()
                }
                iv_hangman.setImageResource(R.drawable.game_hang_man_left_hand)
            }
            3 -> {
                val drawable = iv_hangman.drawable
                if(drawable is AnimatedVectorDrawable) {
                    val animatedVectorDrawable = drawable as AnimatedVectorDrawable
                    animatedVectorDrawable.start()
                } else if (drawable is AnimatedVectorDrawableCompat) {
                    val animatedVectorDrawableCompat = drawable as AnimatedVectorDrawableCompat
                    animatedVectorDrawableCompat.start()
                }
                iv_hangman.setImageResource(R.drawable.game_hang_man_right_hand)
            }
            4 -> {
                val drawable = iv_hangman.drawable
                if(drawable is AnimatedVectorDrawable) {
                    val animatedVectorDrawable = drawable as AnimatedVectorDrawable
                    animatedVectorDrawable.start()
                } else if (drawable is AnimatedVectorDrawableCompat) {
                    val animatedVectorDrawableCompat = drawable as AnimatedVectorDrawableCompat
                    animatedVectorDrawableCompat.start()
                }
                iv_hangman.setImageResource(R.drawable.game_hang_man_left_leg)
            }
            5 -> {
                val drawable = iv_hangman.drawable
                if(drawable is AnimatedVectorDrawable) {
                    val animatedVectorDrawable = drawable as AnimatedVectorDrawable
                    animatedVectorDrawable.start()
                } else if (drawable is AnimatedVectorDrawableCompat) {
                    val animatedVectorDrawableCompat = drawable as AnimatedVectorDrawableCompat
                    animatedVectorDrawableCompat.start()
                }
                iv_hangman.setImageResource(R.drawable.game_hang_man_right_leg)

                Toast.makeText(this, "게임 종료! 패배!", Toast.LENGTH_SHORT).show()
                presenter.sendExitData()
                finish()
            }
        }

        if(deathCount >= 5) {
            deathCount++
        } else {
            deathCount = 0
        }

    }

    override fun rightText(appendText: String, index: Int) {
        tvArray[index].text = appendText
        val tvid = resources.getIdentifier("tv_kb_" + appendText.toLowerCase(), "id", packageName)
        val tv: TextView = findViewById(tvid)
        tv.setBackgroundResource(R.drawable.circle_correct_answer)
        tv.isEnabled = false
    }

    override fun setLength(length: Int) {
        this.length = length
        addEditTexts(length - 1)
    }

    override fun finishGame() {
        Toast.makeText(this, "게임 종료! 메인 화면으로 이동합니다.", Toast.LENGTH_SHORT).show()
        finish()
    }

    override fun finishGameroomActivity() {
        finish()
    }

}
