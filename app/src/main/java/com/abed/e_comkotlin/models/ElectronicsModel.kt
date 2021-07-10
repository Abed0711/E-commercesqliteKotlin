package com.abed.e_comkotlin.models

import java.util.*

data class ElectronicsModel(
    var id: Int = getAutoId(),
    var name: String = "",
    var price:String = ""
){
    companion object{
        fun getAutoId():Int{
            val random = Random()
            return random.nextInt(100)
        }
    }
}