package com.abed.e_comkotlin.adapter

import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.abed.e_comkotlin.R
import com.abed.e_comkotlin.models.ElectronicsModel

class ElectronicsAdapter : RecyclerView.Adapter<ElectronicsAdapter.ElectronicsViewHolder>() {
    private var stdList: ArrayList<ElectronicsModel> = ArrayList()

    private var onClickCartItem:((ElectronicsModel)->Unit)?=null

    fun addItems(items: ArrayList<ElectronicsModel>) {
        this.stdList = items
        notifyDataSetChanged()
    }

    fun setOnclickCart(callback:(ElectronicsModel)->Unit){
        this.onClickCartItem=callback

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ElectronicsViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.card_items_elc, parent, false)
    )


    override fun onBindViewHolder(holder: ElectronicsAdapter.ElectronicsViewHolder, position: Int) {
        val std = stdList[position]
        holder.bindView(std)
        holder.btncartAdd.setOnClickListener{ onClickCartItem?.invoke(std) }
    }

    override fun getItemCount(): Int {
        return stdList.size
    }

    class ElectronicsViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        private var id = view.findViewById<TextView>(R.id.tvId)
        private var pName = view.findViewById<TextView>(R.id.tvName)
        private var pPrice = view.findViewById<TextView>(R.id.tvPrice)
        var btncartAdd = view.findViewById<Button>(R.id.btnAddCart)

        fun bindView(std: ElectronicsModel) {
            id.text = std.id.toString()
            pName.text = std.name
            pPrice.text = std.price
        }


    }
}