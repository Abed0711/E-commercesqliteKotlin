package com.abed.e_comkotlin.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast

import com.abed.e_comkotlin.R
import com.abed.e_comkotlin.RestApi.APIService

import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody

import retrofit2.Retrofit
import okhttp3.RequestBody.Companion.toRequestBody

class createAccountInformation : AppCompatActivity() {
    val retrofit = Retrofit.Builder()
        .baseUrl("http://sandbox.emdexapi.com/api/v1/")
        .build()

    lateinit var view: View
    lateinit var editTextEmail: EditText
    lateinit var editTextPass: EditText
    lateinit var checkBox: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account_information)

        view = findViewById(R.id.rectangle_30)
        editTextEmail = findViewById(R.id.emailId)
        editTextPass = findViewById(R.id.editTextTextPassword)
        checkBox = findViewById(R.id.checkBox)


        view.setOnClickListener(View.OnClickListener setOnClickListener@{
            val email = editTextEmail.text.toString().trim()
            val password = editTextPass.text.toString().trim()
            if (email.isEmpty()) {
                editTextEmail.error = "Email Required"
                editTextEmail.requestFocus()
                return@setOnClickListener
            }
            if (password.isEmpty()) {
                editTextPass.error = "Password Required"
                editTextPass.requestFocus()
                return@setOnClickListener
            }
            registerNewUser(email, password, password, "Hos")
            //login(email,password)
        })


    }

    fun registerNewUser(email: String, password: String, c_password: String, name: String) {
        val service = retrofit.create(APIService::class.java)
        val fields: HashMap<String?, RequestBody?> = HashMap()
        fields["email"] = email.toRequestBody("text/plain".toMediaTypeOrNull())
        fields["password"] = password.toRequestBody("text/plain".toMediaTypeOrNull())
        fields["c_password"] = password.toRequestBody("text/plain".toMediaTypeOrNull())
        fields["name"] = name.toRequestBody("text/plain".toMediaTypeOrNull())

        CoroutineScope(Dispatchers.IO).launch {

            // Do the POST request and get response
            val response = service.registerUser(fields)

            withContext(Dispatchers.Main) {

                if (response.isSuccessful) {
                    val gson = GsonBuilder().setPrettyPrinting().create()
                    val responseData = gson.toJson(
                        JsonParser.parseString(
                            response.body()
                                ?.string()
                        )
                    )
                    Toast.makeText(applicationContext, response.toString(), Toast.LENGTH_SHORT).show()

                } else {

                    Toast.makeText(applicationContext, "User Not Added ..", Toast.LENGTH_SHORT)
                        .show()

                }
            }
        }
    }


    fun login(email: String, password: String) {
        val service = retrofit.create(APIService::class.java)
        val fields: HashMap<String?, RequestBody?> = HashMap()
        fields["email"] = email.toRequestBody("text/plain".toMediaTypeOrNull())
        fields["password"] = password.toRequestBody("text/plain".toMediaTypeOrNull())
        Toast.makeText(applicationContext, fields["email"].toString(), Toast.LENGTH_SHORT).show()

        CoroutineScope(Dispatchers.IO).launch {

            // Do the POST request and get response
            val response = service.loginUser(fields)

            withContext(Dispatchers.Main) {

                if (response.isSuccessful) {
                    val gson = GsonBuilder().setPrettyPrinting().create()
                    val responseData = gson.toJson(
                        JsonParser.parseString(
                            response.body()
                                ?.string()
                        )
                    )
                    Toast.makeText(applicationContext, "Yes!!!", Toast.LENGTH_SHORT).show()

                } else {

                    Toast.makeText(applicationContext, "User Not Added ..", Toast.LENGTH_SHORT)
                        .show()

                }
            }
        }
    }


//    private fun signin(email: String, password: String) {
//        val service = retrofit.create(APIService::class.java)
//        val fields: HashMap<String?, RequestBody?> = HashMap()
//        fields["email"] = email.toRequestBody("text/plain".toMediaTypeOrNull())
//        fields["password"] = password.toRequestBody("text/plain".toMediaTypeOrNull())
//        CoroutineScope(Dispatchers.IO).launch {
//
//            // Do the POST request and get response
//            val response = service.loginUser(fields)
//            Toast.makeText(applicationContext, response.toString(), Toast.LENGTH_SHORT).show()
//            withContext(Dispatchers.Main) {
//
//                if (response.isSuccessful) {
//                    val gson = GsonBuilder().setPrettyPrinting().create()
//                    val responseData = gson.toJson(
//                        JsonParser.parseString(
//                            response.body()
//                                ?.string()
//                        )
//                    )
//                    Toast.makeText(applicationContext, "User Logged In ..", Toast.LENGTH_SHORT).show()
//
//                } else {
//
//                    Toast.makeText(applicationContext, "User Not Logged in ..", Toast.LENGTH_SHORT)
//                        .show()
//
//                }
//            }
//        }
//    }


}