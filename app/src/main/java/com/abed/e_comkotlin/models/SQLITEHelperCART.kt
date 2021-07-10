package com.abed.e_comkotlin.models

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.lang.Exception

class SQLLITEHelperCART(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "eCom1.db"

        val TBL_CART = "tbl_cart"
        val ID_cart = "id"
        val PD_NAME_cart = "pd_name"
        val PD_PRICE_cart = "pd_price"

    }

    override fun onCreate(db: SQLiteDatabase?) {


        val createTbleCom =
            ("CREATE TABLE $TBL_CART($ID_cart INTEGER PRIMARY KEY,$PD_NAME_cart TEXT,$PD_PRICE_cart INTEGER)");


        db?.execSQL(createTbleCom)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TBL_CART")
        onCreate(db)
    }


    fun insertCART(std: CartModel): Long {

        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(ID_cart, std.idCart)
        contentValues.put(PD_NAME_cart, std.nameCart)
        contentValues.put(PD_PRICE_cart, std.priceCart)

        val success = db.insert(TBL_CART, null, contentValues)
        db.close()
        return success
    }

    fun getAllCART(): ArrayList<CartModel> {
        val stdList: ArrayList<CartModel> = ArrayList()
        val selectQuery = "SELECT * FROM $TBL_CART"
        val db = this.readableDatabase

        val cursor: Cursor?

        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: Exception) {
            e.printStackTrace()
            db.execSQL(selectQuery)
            return ArrayList()
        }
        var id: Int
        var name: String
        var price: String

        if (cursor.moveToFirst()) {
            do {
                id = cursor.getInt(cursor.getColumnIndex("id"))
                name = cursor.getString(cursor.getColumnIndex("pd_name"))
                price = cursor.getString(cursor.getColumnIndex("pd_price"))

                val std = CartModel(idCart = id, nameCart = name, priceCart = price)
                stdList.add(std)
            } while (cursor.moveToNext())
        }
        return stdList
    }


}