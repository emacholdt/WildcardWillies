package com.mephisto17games.platzhalterpreise

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.media.AudioManager
import android.media.MediaPlayer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.transition.Scene
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import android.content.Context.AUDIO_SERVICE
import android.support.v4.content.ContextCompat.getSystemService



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val startGameButton = findViewById<Button>(R.id.universalButton)
        startGameButton.text = getString(R.string.startGameText)

        val creditsButton = findViewById<Button>(R.id.creditsButton)

        /*val howToPlayButton = findViewById<Button>(R.id.howToPlayButton)*/

        val player1Name = findViewById<EditText>(R.id.p1DefaultName)

        // Musik im Hintergrund
        // Credits: https://www.bensound.com
        // The Elevator Bossa Nova
        Music.play(this, R.raw.bensound_elevator)

        // Abfragen ob Musik Off -> true
        val musicOff: SharedPreferences = getSharedPreferences("musicOff",0)
        val editor: SharedPreferences.Editor = musicOff.edit()
        music.isChecked = musicOff.getBoolean("musicOff", true)

        // Zugriff auf Audioplayer
        val audioManager: AudioManager = getSystemService(AUDIO_SERVICE) as AudioManager
        // Audio auf 50% schalten
        val media50PercentVolume: Int = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC)*100/50
        // Audio stumm schalten
        val mediaMinVolume: Int = audioManager.getStreamMinVolume(AudioManager.STREAM_MUSIC)

        // Aktivierung des Switch und speichern in Prefs
        music.setOnClickListener(View.OnClickListener {
            if(!music.isChecked){
                Music.stop(this)
                audioManager.setStreamVolume(
                    AudioManager.STREAM_MUSIC, // Stream type
                    mediaMinVolume, // Volume index
                    AudioManager.FLAG_REMOVE_SOUND_AND_VIBRATE// Flags
                )
                editor.putBoolean("musicOff", false)
                editor.commit()
            }else{
                Music.play(this, R.raw.bensound_elevator)
                audioManager.setStreamVolume(
                    AudioManager.STREAM_MUSIC, // Stream type
                    media50PercentVolume, // Volume index
                    AudioManager.FLAG_PLAY_SOUND// Flags
                )
                editor.putBoolean("musicOff", true)
                editor.commit()
            }
        })


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

        // Click on Credits
        creditsButton.setOnClickListener(View.OnClickListener {
            val myIntent = Intent(this@MainActivity, CreditActivity::class.java)
            startActivity(myIntent)
        })

        // Click on How to play?
        /*howToPlayButton.setOnClickListener(View.OnClickListener {
            Toast.makeText(this, "1. Choose your player name and select the difficulty by choosing an easy (Schmal-Lars), " +
                    "normal (Lars), or hard (Breit-Lars) opponent. \n" +
                    "2. Try to guess the \"original\" price as close as possible.\n" +
                    "3. Your bet was closer than the CPU's? Bravo, you earned 1 point. Guess the price correctly to earn the price in " +
                    "points (up to 1000)! \n" +
                    "4. You and Lars take turns guessing first. \n" +
                    "5. Collect more points than your opponent in a game of 5 rounds, or extend to a 15 round marathon.",
                Toast.LENGTH_LONG).show()
        })*/

    }





}

