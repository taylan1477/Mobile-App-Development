package com.taylanozgurozdemir.yemektariflerim

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), YemekEkleFragment.OnYemekEkleListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // MainActivity açıldığında herhangi bir işlem yapmaya gerek yok.
        // NavHostFragment otomatik olarak navigation işlemlerini yönetecek.
    }
    // Yeni yemek ekleme metodu
    override fun onYemekEkle(yeniYemek: Yemek) {
        val fragment = supportFragmentManager.findFragmentById(R.id.fragment_container) as? YemekListFragment
        fragment?.addYemekToList(yeniYemek) // Yeni yemeği listeye ekle
    }
}