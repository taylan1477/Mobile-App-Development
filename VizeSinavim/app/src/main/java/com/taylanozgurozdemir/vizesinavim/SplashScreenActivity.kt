package com.taylanozgurozdemir.vizesinavim

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import com.taylanozgurozdemir.vizesinavim.databinding.ActivitySplashBinding

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Geri sayım
        object : CountDownTimer(5000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                binding.countdownTimer.text = (millisUntilFinished / 1000).toString()
            }

            override fun onFinish() {
                // Login ekranına yönlendirme
                val intent = Intent(this@SplashScreenActivity, LoginActivity::class.java)
                startActivity(intent)
                finish() // Splash ekranını kapat
            }
        }.start()
    }
}
