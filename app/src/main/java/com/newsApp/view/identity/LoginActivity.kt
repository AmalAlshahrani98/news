package com.newsApp.view.identity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
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

lateinit var sharedPref:SharedPreferences
lateinit var sharedPrefEditor:SharedPreferences.Editor
const val USERID = "userId"
var SHARED_PREF_FILE = "preference"

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
        sharedPref = this.getSharedPreferences(SHARED_PREF_FILE, Context.MODE_PRIVATE)
        if(sharedPref.getBoolean("is Loged",false)){
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
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

                                sharedPrefEditor = sharedPref.edit()
                                sharedPrefEditor.putBoolean("log", true)
//                                sharedPrefEditor.putString(USERID,FirebaseAuth.getInstance().currentUser!!.uid)
                                sharedPrefEditor.commit()


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