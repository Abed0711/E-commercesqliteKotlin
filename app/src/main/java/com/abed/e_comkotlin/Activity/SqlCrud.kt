package com.abed.e_comkotlin.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.abed.e_comkotlin.R
import com.abed.e_comkotlin.adapter.ElectronicsAdapter
import com.abed.e_comkotlin.models.ElectronicsModel
import com.abed.e_comkotlin.models.SQLLiteHelper

class SqlCrud : AppCompatActivity() {

    private lateinit var edName: EditText
    private lateinit var edPrice: EditText

    private lateinit var btnAdd: Button
    private lateinit var btnView: Button

    private lateinit var sqliteHelper: SQLLiteHelper

    private lateinit var recyclerView: RecyclerView
    private var adapter: ElectronicsAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sql_crud)

        initView()
        initRecyclerView()
        sqliteHelper = SQLLiteHelper(this)

        btnAdd.setOnClickListener { addElectronics() }
        btnView.setOnClickListener { getElectronics() }

    }

    private fun getElectronics() {
        val edlist = sqliteHelper.getAllElectronics()
        Log.e("abed", "${edlist.size}")
        adapter?.addItems(edlist)
    }


    private fun addElectronics() {

        val name = edName.text.toString()
        val price = edPrice.text.toString()

        if (name.isEmpty() || price.isEmpty()) {
            Toast.makeText(this, "please ENter Requried field", Toast.LENGTH_SHORT).show()
        } else {
            val std = ElectronicsModel(name = name, price = price)
            val status = sqliteHelper.insertElectronics(std)

            //check success or not success

            if (status >= 1) {
                Toast.makeText(this, "Product added", Toast.LENGTH_SHORT).show()
                clearEditText()
                getElectronics()
            } else {
                Toast.makeText(this, "Record not saved", Toast.LENGTH_SHORT).show()

            }


        }
    }

    private fun clearEditText() {
        edName.setText("")
        edPrice.setText("")

        edName.requestFocus()
    }

    private fun initView() {
        edName = findViewById(R.id.edName)
        edPrice = findViewById(R.id.price)
        btnAdd = findViewById(R.id.btnAdd)
        btnView = findViewById(R.id.btnView)
        recyclerView = findViewById(R.id.recyclerViewExp)
    }

    private fun initRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = ElectronicsAdapter()
        recyclerView.adapter = adapter

    }


}