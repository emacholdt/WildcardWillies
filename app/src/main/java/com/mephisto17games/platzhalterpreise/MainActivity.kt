package com.mephisto17games.platzhalterpreise

import android.content.Intent
import android.media.AudioManager
import android.media.MediaPlayer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.transition.Scene
import android.util.Log
import android.view.View
import android.widget.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val startGameButton = findViewById<Button>(R.id.universalButton)
        startGameButton.text = getString(R.string.startGameText)

        val creditsButton = findViewById<Button>(R.id.creditsButton)

        val checkMusic = findViewById<Switch>(R.id.music)

        val player1Name = findViewById<EditText>(R.id.p1DefaultName)

        // Musik im Hintergrund
        // Credits: https://www.bensound.com
        // The Elevator Bossa Nova
        //val mp = MediaPlayer.create(this, R.raw.bensound_elevator)
        //val mp = MediaPlayer.create(this, R.raw.verklixx)
        //mp.start()
        Music.play(this, R.raw.bensound_elevator)

        // Musik auf Lautstärke 0 setzen
        // Get the audio manager system service
/*        val audioManager: AudioManager = getSystemService(AUDIO_SERVICE) as AudioManager
        val media_current_volume: Int = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC)
        music.setOnClickListener(View.OnClickListener {
            if(music.isChecked){
                val volumeIndex = 0
                Music.stop(this)
                audioManager.setStreamVolume(
                    AudioManager.STREAM_MUSIC, // Stream type
                    volumeIndex, // Volume index
                    AudioManager.FLAG_SHOW_UI// Flags
                )
            }else{
                val volumeIndex: Int = media_current_volume
                Music.play(this, R.raw.bensound_elevator)
                audioManager.setStreamVolume(
                    AudioManager.STREAM_MUSIC, // Stream type
                    volumeIndex, // Volume index
                    AudioManager.FLAG_SHOW_UI// Flags
                )
            }
        })*/


        // OnClickListener:
        startGameButton.setOnClickListener(View.OnClickListener {
            val myIntent = Intent(this@MainActivity, gameActivity::class.java)
            val player2Name: String = larsSpinner.selectedItem.toString()
            val difficulty = larsSpinner.selectedItemPosition
            var roundsToPlay = 5

            // Switch

            if(rounds.isChecked){
                roundsToPlay = 15
            }

            Log.d("wildcarcwillies", difficulty.toString())
            myIntent.putExtra("player1Name",player1Name.text.toString())
            myIntent.putExtra("player2Name",player2Name)
            myIntent.putExtra("difficulty",difficulty)
            myIntent.putExtra("rounds", roundsToPlay)

            Music.stop(this)

            startActivity(myIntent)
        })

        creditsButton.setOnClickListener(View.OnClickListener {
            val myIntent = Intent(this@MainActivity, CreditActivity::class.java)
            startActivity(myIntent)
        })

    }





}

