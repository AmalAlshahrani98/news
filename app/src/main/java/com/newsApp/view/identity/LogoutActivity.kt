package com.newsApp.view.identity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.newsApp.R

class LogoutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logout)

        val userIdTextView: TextView = findViewById(R.id.userID_TextView)
        val emailAddressTextView: TextView = findViewById(R.id.emailAddress_TextView)
        val logoutButton: Button = findViewById(R.id.logout_buttonx)

//        مثل الموجود في الرجستر والكتفتي نجمع المعلوملات
        val userId = intent.getStringExtra("UserId")
        val email = intent.getStringExtra("Email")
        // + >> or $
        userIdTextView.text = "UserId" + userId
        emailAddressTextView.text = "Email" + email

        logoutButton.setOnClickListener(){
            // destroy session with firebase >> logout
            FirebaseAuth.getInstance().signOut()
            // move from the secreen to login
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

    }
}