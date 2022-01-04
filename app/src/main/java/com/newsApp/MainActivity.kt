package com.newsApp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.newsApp.repositories.ApiServiceRepository
import com.newsApp.repositories.ApiServiceRepositoryEdit

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ApiServiceRepository.init(this)
        ApiServiceRepositoryEdit.init(this)
        setContentView(R.layout.activity_main)
    }
}