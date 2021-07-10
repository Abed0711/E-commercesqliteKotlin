package com.abed.e_comkotlin.models

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.lang.Exception

class SQLLiteHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "eCom.db"
        private const val TBL_ELECTRONICS = "tbl_electronics"
        private const val ID = "id"
        private const val PD_NAME = "pd_name"
        private const val PD_PRICE = "pd_price"

    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTbleCom =
            ("CREATE TABLE " + TBL_ELECTRONICS + "(" + ID + " INTEGER PRIMARY KEY," + PD_NAME + " TEXT," + PD_PRICE + " INTEGER" + ")")
        db?.execSQL(createTbleCom)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TBL_ELECTRONICS")
        onCreate(db)
    }


    fun insertElectronics(std:ElectronicsModel):Long{
        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(ID,std.id)
        contentValues.put(PD_NAME,std.name)
        contentValues.put(PD_PRICE,std.price)

        val success = db.insert(TBL_ELECTRONICS,null,contentValues)
        db.close()
        return  success
    }

    fun getAllElectronics():ArrayList<ElectronicsModel>{
        val stdList:ArrayList<ElectronicsModel> = ArrayList()
        val selectQuery = "SELECT * FROM $TBL_ELECTRONICS"
        val db = this.readableDatabase

        val cursor: Cursor?

        try {
            cursor = db.rawQuery(selectQuery,null)
        }catch (e: Exception){
            e.printStackTrace()
            db.execSQL(selectQuery)
            return ArrayList()
        }
        var id:Int
        var name:String
        var price:String

        if (cursor.moveToFirst()){
            do {
                id=cursor.getInt(cursor.getColumnIndex("id"))
                name=cursor.getString(cursor.getColumnIndex("pd_name"))
                price=cursor.getString(cursor.getColumnIndex("pd_price"))

                val std = ElectronicsModel(id=id,name=name,price=price)
                stdList.add(std)
            }while (cursor.moveToNext())
        }
        return stdList
    }




}