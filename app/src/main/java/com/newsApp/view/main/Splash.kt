package com.newsApp.view.main

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import com.newsApp.MainActivity
import com.newsApp.R
import com.newsApp.view.identity.LoginActivity
import com.newsApp.view.identity.sharedPref

class Splash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

// Making an Intent


        // Timer for splash
        object : CountDownTimer(2000,1000){
            override fun onTick(p0: Long) {}

            override fun onFinish() {
               sharedPref = getSharedPreferences("pref", Context.MODE_PRIVATE)
                if (sharedPref.getBoolean("log", false)){
                    mainActivity()
                }else{
                    loginActivity()
                }
            }

        }.start()
    }


    fun mainActivity(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun loginActivity(){
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
    }
