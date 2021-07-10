package com.abed.e_comkotlin.models

import java.util.*

data class CartModel(
    var idCart: Int=getAutoId(),
    var nameCart: String = "",
    var priceCart:String = ""
){
    companion object{
        fun getAutoId():Int{
            val random = Random()
            return random.nextInt(100)
        }
    }
}