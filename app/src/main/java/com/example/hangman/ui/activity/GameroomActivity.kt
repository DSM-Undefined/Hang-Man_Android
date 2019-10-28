package com.example.hangman.ui.activity

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.example.hangman.R
import com.example.hangman.contract.GameroomContract
import com.example.hangman.presenter.GameroomPresenter
import kotlinx.android.synthetic.main.activity_decide.*

class GameroomActivity : AppCompatActivity(), View.OnClickListener, GameroomContract.View {

    var tvArray = arrayListOf<TextView>()
    private lateinit var presenter: GameroomPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gameroom)

        presenter = GameroomPresenter(this)
        initViewListener()
        addEditTexts(4)
    }


    private fun addEditTexts(num: Int) {
        val tvcolor = "#2B2A26"
        val linearlayout: LinearLayout = findViewById(R.id.gameroom_letter)
        linearlayout.gravity = Gravity.CENTER

/*
         val gameroom_tv_1 : TextView = TextView(this)
         gameroom_tv_1.setText("A")
         gameroom_tv_1.setTextSize(35F)
         gameroom_tv_1.setTextColor(Color.parseColor(tvcolor))
         gameroom_tv_1.setBackgroundResource(R.drawable.gameroom_edittext_custum)
         linearlayout.addView(gameroom_tv_1)*/


        tvArray = arrayListOf()
        repeat(5) {
            tvArray.add(TextView(this))
        }
        val tvGameroom1 = TextView(this)
        tvGameroom1.text = "  "
        tvGameroom1.textSize = 35F
        for (i in 0..num) {
            tvArray[i].setTextColor(Color.parseColor(tvcolor))
            tvArray[i].setPadding(0, 0, 0, 3)
            tvArray[i].setTextSize(TypedValue.COMPLEX_UNIT_DIP, 35F)
            tvArray[i].setGravity(Gravity.CENTER)
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
            presenter.alphabetsOnClick(view?.text.toString())
        }
    }

    override fun setEditText(text: String) {
        ed_Questions_word.setText(text)
    }

    override fun appendText(appendText: String) {
        //tvArray[0].text = appendText
    }

    override fun wrongText(appendText: String) {
        val tvid = resources.getIdentifier("tv_kb_" + appendText.toLowerCase(), "id", packageName)
        val tv: TextView = findViewById(tvid)
        tv.setBackgroundResource(R.drawable.ic_close_black_24dp)
        tv.isEnabled = false
    }

    override fun rightText(appendText: String, index: Int) {
        tvArray[index].text = appendText
        val tvid = resources.getIdentifier("tv_kb_" + appendText.toLowerCase(), "id", packageName)
        val tv: TextView = findViewById(tvid)
        tv.setBackgroundResource(R.drawable.circle_correct_answer)
        tv.isEnabled = false
    }

}
