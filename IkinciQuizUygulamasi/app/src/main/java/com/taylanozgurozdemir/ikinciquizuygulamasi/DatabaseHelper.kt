package com.taylanozgurozdemir.ikinciquizuygulamasi

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "mydatabase.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "mytable"

        private const val COLUMN_ID = "id"
        private const val COLUMN_TEXT1 = "text1"
        private const val COLUMN_TEXT2 = "text2"
        private const val COLUMN_TEXT3 = "text3"
        private const val COLUMN_CHECKED = "checked"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTableStatement = ("CREATE TABLE $TABLE_NAME (" +
                "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$COLUMN_TEXT1 TEXT," +
                "$COLUMN_TEXT2 TEXT," +
                "$COLUMN_TEXT3 TEXT," +
                "$COLUMN_CHECKED INTEGER)")

        db.execSQL(createTableStatement)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun insertData(text1: String, text2: String, text3: String, isChecked: Boolean) {
        val db = this.writableDatabase
        val contentValues = ContentValues().apply {
            put(COLUMN_TEXT1, text1)
            put(COLUMN_TEXT2, text2)
            put(COLUMN_TEXT3, text3)
            put(COLUMN_CHECKED, if (isChecked) 1 else 0) // Boolean'u integer'a çevirme
        }

        db.insert(TABLE_NAME, null, contentValues)
    }

    fun getAllData(): List<Data> {
        val dataList = mutableListOf<Data>()
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_NAME", null)

        if (cursor.moveToFirst()) {
            do {
                val text1 = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TEXT1))
                val text2 = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TEXT2))
                val text3 = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TEXT3))
                val isChecked = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_CHECKED)) == 1
                dataList.add(Data(text1, text2, text3, isChecked))
            } while (cursor.moveToNext())
        }
        cursor.close()
        return dataList
    }
}
