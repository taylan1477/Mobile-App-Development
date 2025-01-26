package com.taylanozgurozdemir.vizesinavim


import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.taylanozgurozdemir.vizesinavim.databinding.ActivityYemekEkleBinding

class YemekEkleActivity : AppCompatActivity() {
    private lateinit var binding: ActivityYemekEkleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityYemekEkleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.saveButton.setOnClickListener {
            val yemekIsim = binding.isimEditText.text.toString()
            val yemekTarif = binding.tarifEditText.text.toString()
            val yemekMalzemeler = binding.malzemelerEditText.text.toString().split(",")

            if (yemekIsim.isNotEmpty() && yemekTarif.isNotEmpty() && yemekMalzemeler.isNotEmpty()) {
                val yeniYemek = Yemek(yemekIsim, yemekTarif, yemekMalzemeler)
                val intent = intent
                intent.putExtra("yeniYemek", yeniYemek)
                setResult(Activity.RESULT_OK, intent)
                finish()
            } else {
                Toast.makeText(this, "Lütfen tüm alanları doldurun!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
