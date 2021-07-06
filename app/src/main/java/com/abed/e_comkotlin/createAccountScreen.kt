package com.abed.e_comkotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View

class createAccountScreen : AppCompatActivity() {
    lateinit var view: View
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account_screen)

        view = findViewById(R.id.joinbtn);
        view.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, createAccountInformation::class.java)
            startActivity(intent)
        })


    }
}