package com.newsApp.view.identity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.newsApp.MainActivity
import com.newsApp.R

private const val TAG = "LoginActivity"
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
            val email:String = emailAddress.text.toString().trim()
            val password : String = password.text.toString()
            Log.d("LoginActivityLog",email)

            if (email.isNotEmpty() &&password.isNotEmpty() ){
                FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener {
                        task ->

                            if (task.isSuccessful){
                                val firebaseUser: FirebaseUser = task.result!!.user!!
                                Toast.makeText(this,"User Registered Successfully", Toast.LENGTH_SHORT)
                                    .show()

                                val intent = Intent(this, MainActivity::class.java)
                                intent.putExtra("UserId",firebaseUser.uid)
                                intent.putExtra("Email",firebaseUser.email)
                                startActivity(intent)
                                finish()
                           Log.d(TAG, "LOGIN")
                            }
                            else{
                                Toast.makeText(this,task.exception!!.message.toString(), Toast.LENGTH_SHORT)
                                    .show()
                            }

                        }
                    }
            }
        }

    }