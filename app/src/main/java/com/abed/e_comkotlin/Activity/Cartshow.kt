package com.abed.e_comkotlin.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.abed.e_comkotlin.R
import com.abed.e_comkotlin.adapter.CartAdapter
import com.abed.e_comkotlin.models.ElectronicsModel
import com.abed.e_comkotlin.models.SQLLITEHelperCART
import com.abed.e_comkotlin.models.SQLLiteHelper

class Cartshow : AppCompatActivity() {
    private lateinit var sqliteHelper: SQLLiteHelper
    private lateinit var sqliteCartHelper: SQLLITEHelperCART
    private var std: ((ElectronicsModel) -> Unit)? = null


    private lateinit var recyclerView: RecyclerView
    private var adapter: CartAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cartshow)

        recyclerView = findViewById(R.id.recyclerViewcART)
        initRecyclerView()
        sqliteHelper = SQLLiteHelper(this)
        sqliteCartHelper = SQLLITEHelperCART(this)
        getCart()

    }


    private fun getCart() {
        val edlist = sqliteCartHelper.getAllCART()
        Log.e("abed", "${edlist.size}")
        adapter?.addItems(edlist)
    }

    private fun initRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = CartAdapter()
        recyclerView.adapter = adapter

    }
}