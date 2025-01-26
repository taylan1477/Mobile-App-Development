package com.taylanozgurozdemir.ikinciquizuygulamasi

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.sqlite.db.SupportSQLiteOpenHelper
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var editText1: EditText
    private lateinit var editText2: EditText
    private lateinit var editText3: EditText
    private lateinit var checkBox: CheckBox
    private lateinit var listView: ListView
    private lateinit var saveButton: Button
    private lateinit var databaseHelper: DatabaseHelper // Veritabanı yardımcı sınıfı
    private lateinit var dataList: MutableList<String> // Verilerin tutulacağı liste

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Giriş alanlarını ve butonu tanımlayın
        editText1 = findViewById(R.id.editText1)
        editText2 = findViewById(R.id.editText2)
        editText3 = findViewById(R.id.editText3)
        checkBox = findViewById(R.id.checkBox)
        listView = findViewById(R.id.listView)
        saveButton = findViewById(R.id.saveButton)

        // Veritabanı yardımcı sınıfını başlatın
        databaseHelper = DatabaseHelper(this)

        // Kaydet butonuna tıklama olayını ayarlayın
        saveButton.setOnClickListener {
            saveData()
            loadData() // Verileri yükle
        }

        loadData() // Uygulama açıldığında verileri yükle
    }

    private fun saveData() {
        val text1 = editText1.text.toString()
        val text2 = editText2.text.toString()
        val text3 = editText3.text.toString()
        val isChecked = checkBox.isChecked

        // Veritabanına kaydetme işlemi
        databaseHelper.insertData(text1, text2, text3, isChecked)

        // Giriş alanlarını temizle
        editText1.text.clear()
        editText2.text.clear()
        editText3.text.clear()
        checkBox.isChecked = false
    }

    private fun loadData() {
        // Veritabanından tüm verileri çek
        val allData = databaseHelper.getAllData()

        // Checkbox durumunu değerlendir ve formatla
        dataList = allData.map { data ->
            if (data.isChecked) {
                "${data.text1} - ${data.text2} - ${data.text3} - True"
            } else {
                "${data.text1} - ${data.text2} - ${data.text3} - False"
            }
        }.toMutableList()

        // ListView için adaptör oluştur ve bağla
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, dataList)
        listView.adapter = adapter
    }
}