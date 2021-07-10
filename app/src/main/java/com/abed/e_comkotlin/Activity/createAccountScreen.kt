package com.abed.e_comkotlin.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.abed.e_comkotlin.R
import com.abed.e_comkotlin.Session.LoginPref

class createAccountScreen : AppCompatActivity() {
    lateinit var view: View
    lateinit var view_login: View
    lateinit var session: LoginPref
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account_screen)

        session = LoginPref(this)
        if (session.isLoggedIn()) {
            var i: Intent = Intent(applicationContext, homeScreen::class.java)
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(i)
            finish()
        }



        view = findViewById(R.id.joinbtn);
        view.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, createAccountInformation::class.java)
            startActivity(intent)
        })

        view_login = findViewById(R.id.loginbtn);
        view_login.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, login_screen::class.java)
            startActivity(intent)
        })


    }
}