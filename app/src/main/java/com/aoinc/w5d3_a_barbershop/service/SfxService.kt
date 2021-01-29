package com.aoinc.w5d3_a_barbershop.service

import android.app.IntentService
import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.util.Log
import com.aoinc.w5d3_a_barbershop.R
import com.aoinc.w5d3_a_barbershop.util.Constants

class SfxService : IntentService("SFX Service") {

    private lateinit var mediaPlayer: MediaPlayer

    override fun onHandleIntent(intent: Intent?) {
        val sfxID = intent?.getIntExtra(Constants.SFX_KEY, 0) ?: R.raw.cash_register
        mediaPlayer = MediaPlayer.create(this, sfxID)
        mediaPlayer.start()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}