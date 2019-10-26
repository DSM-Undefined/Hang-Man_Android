package com.example.hangman.ui.activity

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.marginBottom
import androidx.core.view.marginLeft
import com.example.hangman.R
import com.example.hangman.contract.GameroomContract
import com.example.hangman.presenter.GameroomPresenter
import kotlinx.android.synthetic.main.activity_decide.*

class GameroomActivity : AppCompatActivity(), View.OnClickListener, GameroomContract.View{
    private lateinit var gamePresenter: GameroomPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gameroom)

        gamePresenter = GameroomPresenter(this)
        initViewListener()
        addedittext(3)
    }


     private fun addedittext(num : Int){
         val tvcolor ="#2B2A26"
         val linearlayout : LinearLayout = findViewById(R.id.gameroom_letter)
         linearlayout.gravity = Gravity.CENTER

/*
         val gameroom_tv_1 : TextView = TextView(this)
         gameroom_tv_1.setText("A")
         gameroom_tv_1.setTextSize(35F)
         gameroom_tv_1.setTextColor(Color.parseColor(tvcolor))
         gameroom_tv_1.setBackgroundResource(R.drawable.gameroom_edittext_custum)
         linearlayout.addView(gameroom_tv_1)*/


         val tvArray = arrayListOf<TextView>()
         repeat(4) {
             tvArray.add(TextView(this))
         }
         val gameroom_tv_1 = TextView(this)
         gameroom_tv_1.setText("  ")
         gameroom_tv_1.setTextSize(35F)
         tvArray[0].setText("A ")
         tvArray[1].setText("B ")
         tvArray[2].setText("C ")
         tvArray[3].setText("D ")
         for (i in 0..num){
             tvArray[i].setTextColor(Color.parseColor(tvcolor))
             tvArray[i].setPadding(0,0,0,3)
             tvArray[i].setTextSize(TypedValue.COMPLEX_UNIT_DIP, 35F)
             tvArray[i].setGravity(Gravity.CENTER)
             tvArray[i].setBackgroundResource(R.drawable.gameroom_edittext_custum)
         }
         for (i in 0..num){
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

    override fun onClick(p0: View?) {

    }
}
