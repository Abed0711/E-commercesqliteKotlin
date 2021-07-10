package com.abed.e_comkotlin.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.abed.e_comkotlin.R
import com.abed.e_comkotlin.adapter.ElectronicsAdapter
import com.abed.e_comkotlin.models.CartModel
import com.abed.e_comkotlin.models.ElectronicsModel
import com.abed.e_comkotlin.models.SQLLITEHelperCART
import com.abed.e_comkotlin.models.SQLLiteHelper

class ExploreData : AppCompatActivity() {
    private lateinit var sqliteHelper: SQLLiteHelper
    private lateinit var sqliteCartHelper: SQLLITEHelperCART
    private var std: ((ElectronicsModel) -> Unit)? = null




    private lateinit var recyclerView: RecyclerView
    private var adapter: ElectronicsAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_explore_data)



        recyclerView = findViewById(R.id.recyclerViewExpTest)
        initRecyclerView()
        sqliteHelper = SQLLiteHelper(this)
        sqliteCartHelper = SQLLITEHelperCART(this)
        getElectronics()

        adapter?.setOnclickCart {
            addcart(it.id,it.name,it.price)

        }



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

    private fun addcart(id: Int, name: String, price: String) {

        val nameC = name
        val priceC = price

        if (nameC.isEmpty() || priceC.isEmpty()) {
            Toast.makeText(this, "please ENter Requried field", Toast.LENGTH_SHORT).show()
        } else {
            val std = CartModel(nameCart = nameC, priceCart = priceC)
            val status = sqliteCartHelper.insertCART(std)
            Toast.makeText(this, std.nameCart, Toast.LENGTH_SHORT).show()

            //check success or not success

            if (status >= 1) {
                Toast.makeText(this, "Product added", Toast.LENGTH_SHORT).show()

                getElectronics()
            } else {
                Toast.makeText(this, "Record not saved", Toast.LENGTH_SHORT).show()

            }


        }
    }
}