package com.newsApp.view.identity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.newsApp.R

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

       val emailAddress:EditText = findViewById(R.id.emailAddress_EditText)
        val password:EditText = findViewById(R.id.password_EditText)
        val loginButton:Button = findViewById(R.id.login_button)
       val registerTextView:TextView = findViewById(R.id.register_TextView)

        registerTextView.setOnClickListener {
            startActivity(Intent(this,RegisterActivity::class.java))
            finish()

        }
        loginButton.setOnClickListener(){
            val email:String = emailAddress.text.toString()
            val password : String = password.text.toString()
            if (email.isNotEmpty() &&password.isNotEmpty() ){
                FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(){
                        task ->
                        if (task.isSuccessful){

                        }
                    }
            }
        }

    }
}