package com.abed.e_comkotlin.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.abed.e_comkotlin.R
import com.abed.e_comkotlin.adapter.ElectronicsAdapter
import com.abed.e_comkotlin.models.SQLLiteHelper

class ExploreData : AppCompatActivity() {
    private lateinit var sqliteHelper: SQLLiteHelper

    private lateinit var recyclerView: RecyclerView
    private var adapter: ElectronicsAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_explore_data)

        recyclerView = findViewById(R.id.recyclerViewExpTest)



        initRecyclerView()
        sqliteHelper = SQLLiteHelper(this)

        getElectronics()


    }

    private fun getElectronics() {
        val edlist = sqliteHelper.getAllElectronics()
        Log.e("abed", "${edlist.size}")
        adapter?.addItems(edlist)
    }

    private fun initRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = ElectronicsAdapter()
        recyclerView.adapter = adapter

    }
}