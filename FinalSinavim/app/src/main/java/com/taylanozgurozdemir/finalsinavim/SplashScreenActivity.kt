package com.taylanozgurozdemir.finalsinavim

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import com.taylanozgurozdemir.finalsinavim.databinding.ActivitySplashBinding

import android.view.animation.AnimationUtils

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Fade-in animasyonunu başlat
        val slideUpFadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        binding.homeImage.startAnimation(slideUpFadeInAnimation)

        // Geri sayım
        object : CountDownTimer(6000, 1000) {
            override fun onTick(millisUntilFinished: Long) {

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
