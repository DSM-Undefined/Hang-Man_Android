package com.example.hangman.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.TextView
import com.example.hangman.R
import com.example.hangman.contract.DecideContract
import com.example.hangman.presenter.DecidePresenter
import kotlinx.android.synthetic.main.activity_decide.*
import java.security.AccessController.getContext

class DecideActivity : AppCompatActivity(), View.OnClickListener, DecideContract.View {
    private lateinit var decidePresenter: DecidePresenter
    private var editTextData = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_decide)

        decidePresenter = DecidePresenter(this)

        initTextViewOnClick()

    }

    override fun appendText(appendText: String) {
        editTextData += appendText
        ed_Questions_word.setText(editTextData)
    }

    private fun initTextViewOnClick() {
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
            editTextData = ed_Questions_word.text.toString()
            if (editTextData.length < 10) decidePresenter.alphabetsOnClick(view?.text.toString())
        }
        else if(view?.id == R.id.btn_submit) {
            // TODO : editTextData 가 2 ~ 10 사이가 아니면 경고, 맞으면 startActivity 로직을 Presenter 에 작성.
        }
    }
}

//        btn_submit.setOnClickListener{
//            val shack : Animation = AnimationUtils.loadAnimation(this, R.anim.dicide_an_error)
//            ed_Questions_word.startAnimation(shack)
//        }
