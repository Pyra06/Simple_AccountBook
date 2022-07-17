package com.example.accountbookv1

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

var DATABASE_NAME = "MyDB.db"
var TABLE_NAME = "Users"
var COL_TYPE = "type"
var COL_NAME = "name"
var COL_ID = "id"

class DataBaseHandler(private var context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {

        val createTable = "CREATE TABLE " + TABLE_NAME + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_NAME + " VARCHAR(100), " +
                COL_TYPE + " CHAR(1))"

        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    fun insertData(user: User) {
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(COL_NAME, user.name)
        cv.put(COL_TYPE, user.type)
        val result = db.insert(TABLE_NAME, null, cv)

        if(result == (-1).toLong()) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
        }
        db.close()
    }

//    fun readData() : MutableList<User> {
//        val list: MutableList<User> = ArrayList()
//
//        val db = this.readableDatabase
//        val query = "Select * from " + TABLE_NAME
//        val result = db.rawQuery(query, null)
//
//        if (result.moveToFirst()) {
//            do {
//                val user = User()
//                user.id = result.getString(result.getColumnIndex(COL_ID)).toInt()
//                user.name = result.getString(result.getColumnIndex(COL_NAME))
//                user.type = result.getString(result.getColumnIndex(COL_TYPE))
//                list.add(user)
//            } while (result.moveToNext())
//        }
//
//        result.close()
//        db.close()
//        return list
//    }

//    fun getUsers():ArrayList<HashMap<String, String>> {
//        val db = this.writableDatabase
//        val userList = ArrayList<HashMap<String, String>>()
//        val query = "SELECT id, name, type FROM " + TABLE_NAME
//        val cursor = db.rawQuery(query, null)
//        while (cursor.moveToNext())
//        {
//            val user = HashMap<String,String>()
//            user["id"] = cursor.getString(cursor.getColumnIndex(COL_ID))
//            user["name"] = cursor.getString(cursor.getColumnIndex(COL_NAME))
//            user["type"] = cursor.getString(cursor.getColumnIndex(COL_TYPE))
//            userList.add(user)
//        }
//        return userList
//    }

    fun getUser(nCtx : Context) : ArrayList<User> {

        val db = this.readableDatabase
        val query = "Select * from " + TABLE_NAME
        val result = db.rawQuery(query, null)
        val userList = ArrayList<User>()

        if(result.count == 0) {
            Toast.makeText(nCtx, "No Accounts To Show", Toast.LENGTH_SHORT).show()
        } else {
            while (result.moveToNext()){
                val user = User()
                user.id = result.getInt(result.getColumnIndex(COL_ID))
                user.name = result.getString(result.getColumnIndex(COL_NAME))
                user.type = result.getString(result.getColumnIndex(COL_TYPE))
                userList.add(user)
            }
            Toast.makeText(nCtx, "${result.count.toString()} Accounts Found", Toast.LENGTH_SHORT).show()
        }
        result.close()
        db.close()
        return userList
    }

    fun deleteData(id: Int) {
        val db = this.readableDatabase

        db.delete(TABLE_NAME, "ID = ?", arrayOf(id.toString()))
        db.close()
    }
}