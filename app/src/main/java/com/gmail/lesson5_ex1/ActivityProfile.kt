package com.gmail.lesson5_ex1

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class ActivityProfile : AppCompatActivity() {
    var imgURI: Uri? = null
    val IMG_PICK_CODE = 100
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        if(intent != null){
            val username = intent.getStringExtra("usernameValue")
            findViewById<TextView>(R.id.usernameTV).text = username
        }
        findViewById<Button>(R.id.backBtn).setOnClickListener{
            val intent = Intent()
            if(imgURI != null) {
                intent.putExtra("imgURI", imgURI.toString())
            }
            else {
                intent.putExtra("imgURI","")
            }
            setResult(Activity.RESULT_OK,intent)
            finish()
        }
        findViewById<Button>(R.id.sendEmailBtn).setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            intent.data = Uri.parse("mailto:")
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_SUBJECT,"Hello from Android class")
            intent.putExtra(Intent.EXTRA_TEXT,"This is an example of the body of email ")
            intent.putExtra(Intent.EXTRA_EMAIL, arrayOf("truongson2k@gmail.com"))
            startActivity(Intent.createChooser(intent,"Send email via ..."))
        }
        findViewById<Button>(R.id.chooseLogoBtn).setOnClickListener{
            val imgIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(imgIntent,IMG_PICK_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == IMG_PICK_CODE && resultCode == Activity.RESULT_OK){
            imgURI = data?.data
            findViewById<ImageView>(R.id.profileActivityIV).setImageURI(imgURI)
        }
    }

}