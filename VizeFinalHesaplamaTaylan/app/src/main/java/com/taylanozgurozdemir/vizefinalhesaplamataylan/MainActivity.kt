package com.taylanozgurozdemir.vizefinalhesaplamataylan

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import android.widget.EditText
import android.widget.TextView
import android.widget.ImageView
import android.graphics.Color

class MainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n", "MissingInflatedId", "DefaultLocale")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // id'leri alıyorum
        val firstNumberEditText = findViewById<EditText>(R.id.editTextNumber1)
        val secondNumberEditText = findViewById<EditText>(R.id.editTextNumber2)
        val submitButton = findViewById<Button>(R.id.buttonHesapla)
        val sonucText = findViewById<TextView>(R.id.sonucText)
        val balditext = findViewById<TextView>(R.id.baldiText)
        val imageView = findViewById<ImageView>(R.id.imageView3)

        // Butona tıklayınca
        submitButton.setOnClickListener {
            // sayıları al
            val VizeNotu = firstNumberEditText.text.toString().toIntOrNull()
            val FinalNotu = secondNumberEditText.text.toString().toIntOrNull()
            // hesaplama işlemini yap ve sonuc a yaz
            if (VizeNotu != null && FinalNotu != null && VizeNotu <= 100 && FinalNotu <= 100) {
                val sonuc:Double = (VizeNotu*0.4)+(FinalNotu*0.6).toDouble()
                sonucText.text = String.format("%.1f", sonuc)
                // geçtik mi kaldık mı? finalden 55 üstü almaya dikkat
                if (FinalNotu!! >= 55 && (VizeNotu!!*0.4)+(FinalNotu!!*0.6) >= 50) {
                    balditext.text = "Aferin GEÇTİN!!!"
                    sonucText.setTextColor(Color.GREEN)
                    imageView.setImageResource(R.drawable.happy)
                    balditext.setTextColor(Color.GREEN)
                }
                else if (FinalNotu!! >= 55 && (VizeNotu!!*0.4)+(FinalNotu!!*0.6) >= 45) {
                    balditext.text = "Şartlı GEÇTİN!"
                    sonucText.setTextColor(Color.GREEN)
                    imageView.setImageResource(R.drawable.happy)
                    balditext.setTextColor(Color.YELLOW)
                }
                else if (FinalNotu!! < 55 || (VizeNotu!!*0.4)+(FinalNotu!!*0.6) < 50) {
                    balditext.text = "Maalesef KALDIN!!!"
                    sonucText.setTextColor(Color.RED)
                    imageView.setImageResource(R.drawable.sad)
                    balditext.setTextColor(Color.RED)
                }
            }
            // 100 den yukarı sayıları girilirse sonuca uyarı mesajı yaz
            else if (VizeNotu != null && FinalNotu != null && (VizeNotu > 100 || FinalNotu > 100)) {
                sonucText.text = "Yanlış Girdi!!"
                balditext.text = "Düzgün Sayılar GİR!!!"
                imageView.setImageResource(R.drawable.sad)
                balditext.setTextColor(Color.RED)
                sonucText.setTextColor(Color.BLACK)
            }
        }
    }
}


