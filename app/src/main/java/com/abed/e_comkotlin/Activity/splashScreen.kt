package com.abed.e_comkotlin.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.abed.e_comkotlin.R

@Suppress("DEPRECATION")
class splashScreen : AppCompatActivity() {
    lateinit var handler: Handler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        handler = Handler()
        handler.postDelayed({
            val intent = Intent(this, createAccountScreen::class.java)
            startActivity(intent)
            finish()
        }, 3000)

    }
}

