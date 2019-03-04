package com.mephisto17games.platzhalterpreise

import android.content.Context
import android.media.MediaPlayer
import android.os.Build.VERSION_CODES.M
import android.preference.Preference


object Music {
    //adding a variable to store the time of music being played.
    private var pos = 0
    private var mp: MediaPlayer? = null

    fun play(context: Context, resource: Int) {
        stop(context)
        // Start music
            mp = MediaPlayer.create(context, resource)
            mp!!.isLooping = true
            mp!!.start()
            //this will continue the music from wherever it was paused
            mp!!.seekTo(pos)
    }

    fun stop(context: Context) {

        if (mp != null) {
            mp!!.pause()//to pause the music
            //to store current pause time in pos
            //as pos is static it will retain the value
            pos = mp!!.currentPosition
            mp!!.stop()
            mp!!.release()
            mp = null
        }
    }
}