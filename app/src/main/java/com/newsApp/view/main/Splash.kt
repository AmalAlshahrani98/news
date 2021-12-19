package com.newsApp.view.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import com.newsApp.MainActivity
import com.newsApp.R
import com.newsApp.view.identity.LoginActivity

class Splash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

// Making an Intent
        val intent = Intent(this, LoginActivity::class.java)

        // Timer for splash
        object : CountDownTimer(2000,1000){
            override fun onTick(p0: Long) {}

            override fun onFinish() {
                startActivity(intent)
            }

        }.start()
    }


    }
