package com.newsApp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.newsApp.repositories.ApiServiceRepository

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ApiServiceRepository.init(this)
        setContentView(R.layout.activity_main)
    }
}