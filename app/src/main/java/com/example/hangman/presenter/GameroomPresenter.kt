package com.example.hangman.presenter

import android.util.Log
import com.example.hangman.contract.GameroomContract
import com.example.hangman.ui.activity.GameroomActivity

class GameroomPresenter(private val view: GameroomActivity) : GameroomContract.Presenter {
     override fun alphabetsOnClick(text: String) {
         Log.d("if",text)
         //view.appendText(text)
         if(text.equals("A")){
             view.rightText(text,0)
             Log.d("if문","작동")
         }else if(text == "P"){
             view.rightText(text,1)
             view.rightText(text,2)
         }else if(text == "L"){
             view.rightText(text,3)
         }else if(text == "E"){
             view.rightText(text,4)
         }else{
             view.wrongText(text)

         }
     }
}