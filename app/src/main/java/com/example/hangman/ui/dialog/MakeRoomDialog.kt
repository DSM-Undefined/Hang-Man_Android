package com.example.hangman.ui.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import com.example.hangman.R

class MakeRoomDialog(context: Context): Dialog(context){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setCanceledOnTouchOutside(true)
        window?.setBackgroundDrawable(ColorDrawable())
        window?.setDimAmount(0.8f)

        setContentView(R.layout.dialog_make_room)
    }

}