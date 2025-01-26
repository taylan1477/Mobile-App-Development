package com.taylanozgurozdemir.vizesinavim

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.taylanozgurozdemir.vizesinavim.databinding.ActivityYemekDetayBinding

class YemekDetayActivity : AppCompatActivity() {
    private lateinit var binding: ActivityYemekDetayBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityYemekDetayBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Intent'ten yemeği al
        val yemekIsim = intent.getStringExtra("yemekIsim")
        val yemekTarif = intent.getStringExtra("yemekTarif")
        val yemekMalzemeler = intent.getStringArrayListExtra("yemekMalzemeler")

        // Verileri ekrana aktar
        binding.textYemekAdi.text = yemekIsim
        binding.textYemekTarifi.text = yemekTarif
        binding.textYemekMalzemeleri.text = yemekMalzemeler?.joinToString("\n")
    }
}
