package com.newsApp

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.firebase.auth.FirebaseAuth
import com.newsApp.databinding.ActivityMainBinding
import com.newsApp.repositories.ApiServiceRepository
import com.newsApp.repositories.ApiServiceRepositoryEdit
import com.newsApp.view.SavedNewsFragment
import com.newsApp.view.identity.LoginActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    private val CHANNEL_ID ="channel_id_example_01"
    private val notification = 101


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

        createNotificationChannel()
        sendNotification()


    }
    // notification channel
    private fun createNotificationChannel(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val title = "Notification Title"
            val bodyText = "Notification body"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel : NotificationChannel = NotificationChannel(CHANNEL_ID,title,importance).apply {
                description = bodyText
            }
            val notificationManager : NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE)
                    as NotificationManager
            notificationManager.createNotificationChannel(channel)

        }
    }

    private fun sendNotification(){
        val intent = Intent(this,SavedNewsFragment::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, 0)
        val builder = NotificationCompat.Builder(this,CHANNEL_ID)
            .setSmallIcon(R.drawable.iconsnews)
            .setContentTitle("new news")
            .setContentText("lets read")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            // Set the intent that will fire when the user taps the notification
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        with(NotificationManagerCompat.from(this)){
            notify(notification, builder.build())
        }
//
    }}