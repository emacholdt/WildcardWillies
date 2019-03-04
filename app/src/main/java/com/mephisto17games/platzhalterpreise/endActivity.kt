package com.mephisto17games.platzhalterpreise

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.shapes.Shape
import android.media.MediaPlayer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.final_score.*
import nl.dionsegijn.konfetti.models.Size

class endActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.final_score)

        val returnHomeButton = findViewById<Button>(R.id.universalButton)
        returnHomeButton.text = "Back to Start"

        val winnerName = findViewById<TextView>(R.id.winnerPlayerName)
        val winnerScore = findViewById<TextView>(R.id.winnerScore)
        val loserName = findViewById<TextView>(R.id.loserPlayerName)
        val loserScore = findViewById<TextView>(R.id.loserPlayerScore)


        val bundle = intent.extras
        val wName = bundle.getString("winnerName")
        winnerName.text = wName
        val wScore = bundle.getString("winnerScore")
        winnerScore.text = wScore
        val lName = bundle.getString("loserName")
        loserName.text = lName
        val lScore = bundle.getString("loserScore")
        loserScore.text = lScore
        //player1Name.text = name.toString()


        // viele Konfettos
        val viewKonfetti = findViewById<nl.dionsegijn.konfetti.KonfettiView>(R.id.viewKonfetti)
        viewKonfetti.build()
            .addColors(Color.YELLOW, Color.GREEN, Color.MAGENTA)
            .setDirection(0.0, 359.0)
            .setSpeed(1f, 5f)
            .setFadeOutEnabled(true)
            .setTimeToLive(2000L)
            .addShapes(nl.dionsegijn.konfetti.models.Shape.RECT, nl.dionsegijn.konfetti.models.Shape.CIRCLE)
            .addSizes(Size(12))
            .setPosition(100f, viewKonfetti.width.toFloat(), -50f, -50f)
            .streamFor(30, 6000L)

        // Song
        //val mp = MediaPlayer.create(this, R.raw.punky_clap)
        //mp.start()
        Music.play(this, R.raw.punky_clap)

        // OnClickListener:
        returnHomeButton.setOnClickListener(View.OnClickListener {
            val myIntent = Intent(this@endActivity, MainActivity::class.java)

            Music.stop(this)

            startActivity(myIntent)
        })
    }


   // .setPosition(-50f, viewKonfetti.width + 50f, -50f, -50f)

}