package com.example.hangman.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
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
