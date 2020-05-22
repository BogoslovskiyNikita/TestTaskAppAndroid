package com.example.testtaskappandroid

import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_profile.*
import java.net.URI

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        Toast.makeText(this, "Успешная авторизация", Toast.LENGTH_LONG).show()
        val nameTextView: TextView = findViewById(R.id.Name) as TextView
        nameTextView.text = intent.getStringExtra("name");

        val emailView: TextView = findViewById(R.id.emailView) as TextView
        emailView.text = intent.getStringExtra("email")

        profilePicture.setImageBitmap(
            BitmapFactory.decodeResource(
                this.getResources(),
                Integer.parseInt(intent.getStringExtra("picture"))
            )
        )


        button2.setOnClickListener() {
            val intentBack = Intent(this, AuthorizationActivity::class.java)
            startActivity(intentBack)
        }


    }
}

//private fun Intent.getIntExtra(s: String): Int {
//    return R.drawable.error;
//}

