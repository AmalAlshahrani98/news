package com.newsApp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.newsApp.databinding.ActivityMainBinding
import com.newsApp.repositories.ApiServiceRepository
import com.newsApp.repositories.ApiServiceRepositoryEdit

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ApiServiceRepository.init(this)
        ApiServiceRepositoryEdit.init(this)


        val navHost = supportFragmentManager.findFragmentById(R.id.fragmentContainerView)
        as NavHostFragment
        val navController = navHost.navController

        NavigationUI.setupWithNavController(binding.bottomNavigationView,navController)
    }
}