package com.gmail.lesson5_ex1

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    val PROFILE_CODE = 200
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.loginBtn).setOnClickListener{
            val username = findViewById<EditText>(R.id.usernameET).text.toString()
            val password = findViewById<EditText>(R.id.passwordET).text.toString()
            if(username.indexOf("a")==0 && password == "abc"){
                val profileIntent = Intent(applicationContext,ActivityProfile::class.java)
                profileIntent.putExtra("usernameValue",username)
                startActivityForResult(profileIntent,PROFILE_CODE)
            }
            else{
                findViewById<TextView>(R.id.pleaseLoginTV).text = "Wrong password, try again!"
            }
        }
    }
    override fun onResume() {
        super.onResume()
        findViewById<TextView>(R.id.usernameET).text = ""
        findViewById<TextView>(R.id.passwordET).text = ""
        findViewById<TextView>(R.id.pleaseLoginTV).text = "Please login"
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == PROFILE_CODE){
            val imgStr = data?.getStringExtra("imgURI")
            if(imgStr != "") {
                val imgURI = Uri.parse(imgStr)
                findViewById<ImageView>(R.id.mainActivityIV)
                    .setImageURI(imgURI)
            }

        }
    }
}