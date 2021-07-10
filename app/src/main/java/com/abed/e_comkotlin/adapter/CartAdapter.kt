package com.abed.e_comkotlin.adapter

import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.abed.e_comkotlin.R
import com.abed.e_comkotlin.models.CartModel
import com.abed.e_comkotlin.models.ElectronicsModel

class CartAdapter : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {
    private var stdList: ArrayList<CartModel> = ArrayList()

    private var onClickCartItem:((CartModel)->Unit)?=null

    fun addItems(items: ArrayList<CartModel>) {
        this.stdList = items
        notifyDataSetChanged()
    }

    fun setOnclickCart(callback:(CartModel)->Unit){
        this.onClickCartItem=callback

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CartViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.cart_show, parent, false)
    )


    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val std = stdList[position]
        holder.bindView(std)
        //holder.btncartAdd.setOnClickListener{ onClickCartItem?.invoke(std) }
    }

    override fun getItemCount(): Int {
        return stdList.size
    }

    class CartViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        private var id = view.findViewById<TextView>(R.id.cId)
        private var pName = view.findViewById<TextView>(R.id.cName)
        private var pPrice = view.findViewById<TextView>(R.id.cPrice)
        //var btncartAdd = view.findViewById<Button>(R.id.btnAddCart)

        fun bindView(std: CartModel) {
            id.text = std.idCart.toString()
            pName.text = std.nameCart
            pPrice.text = std.priceCart
        }


    }
}