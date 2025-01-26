package com.taylanozgurozdemir.yemektariflerim

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.taylanozgurozdemir.yemektariflerim.databinding.ActivitySplashBinding

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    private var countdownTime = 5 // Geri sayım süresi (saniye)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater) // ViewBinding'ı başlat
        setContentView(binding.root) // Layout'u ayarla

        // Geri sayım zamanlayıcısını başlat
        startCountdown()
    }

    private fun startCountdown() {
        val handler = Handler()

        // Her saniye geri sayımı güncelle
        handler.postDelayed(object : Runnable {
            @SuppressLint("SetTextI18n")
            override fun run() {
                if (countdownTime > 0) {
                    binding.countdownTimer.text = "Uygulama açılıyor... $countdownTime"
                    countdownTime--
                    handler.postDelayed(this, 1000) // 1 saniye sonra tekrar çalıştır
                } else {
                    // Geri sayım tamamlandığında MainActivity'ye geçiş yap
                    val intent = Intent(this@SplashActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish() // SplashActivity'yi kapat
                }
            }
        }, 1000) // İlk çalıştırma için 1 saniye bekle
    }
}