package com.mephisto17games.platzhalterpreise

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_credits.view.*
import kotlinx.android.synthetic.main.activity_main.*

class CreditActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_credits)

        val backButton = findViewById<Button>(R.id.backButton)

        val creditsText = findViewById<TextView>(R.id.creditsText)

        creditsText.text = "Credits\n" +
                "\n"+
                "Developed for MiniBeansJam3 \nby Mephisto17Games\n" +
                "\n" +
                "free pictures from:\n" +
                "https://unsplash.com\n" +
                "https://www.pexels.com\n" +
                "\n" +
                "Budikopf picture\n" +
                "https://opengameart.org/content/budikopf-3d  by Chewbatrij\n" +
                "\n" +
                "RBTV Supporters Club Logo\n" +
                "https://rocketbeans.tv/supporte-uns\n" +
                "\n" +
                "Confetti animation:\n" +
                "ISC License - Copyright (c) 2017 Dion Segijn   https://github.com/DanielMartinus/Konfetti\n" +
                "\n" +
                "Music:\n" +
                "https://www.bensound.com\n"+
                "\n" +
                "Prices:\n" +
                "sloppy google search\n" +
                "completely fictitious\n"


        // OnClickListener:
        backButton.setOnClickListener(View.OnClickListener {
            val myIntent = Intent(this@CreditActivity, MainActivity::class.java)
            startActivity(myIntent)
        })
    }
}