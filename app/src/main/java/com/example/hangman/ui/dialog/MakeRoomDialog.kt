package com.example.hangman.ui.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Point
import android.os.Bundle
import android.view.View
import android.widget.SeekBar
import com.example.hangman.R
import com.example.hangman.util.MakeRoomDialogListener
import kotlinx.android.synthetic.main.dialog_makeroom.*

class MakeRoomDialog(context: Context) : Dialog(context), View.OnClickListener {

    private lateinit var listener: MakeRoomDialogListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_makeroom)

        setCanceledOnTouchOutside(true)
        window?.setDimAmount(0.8f)

        seekbar_makeroom.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                tv_count_makeroom.text = "${p1}명"
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {

            }

        })

        button_makeroom_makeroom.setOnClickListener(this)
        button_makeroom_cancel.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.button_makeroom_cancel -> dismiss()
            R.id.button_makeroom_makeroom -> listener.onClickMakeRoom()
        }
    }

    fun setListener(listener: MakeRoomDialogListener){
        this.listener = listener
    }
}