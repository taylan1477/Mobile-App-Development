package com.taylanozgurozdemir.catchthejoker

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    @OptIn(DelicateCoroutinesApi::class)
    @SuppressLint("SetTextI18n", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // id'leri alıyorum
        val timet = findViewById<TextView>(R.id.textView2)
        val timea = findViewById<TextView>(R.id.textView3)
        val skort = findViewById<TextView>(R.id.textView5)
        val skora = findViewById<TextView>(R.id.textView6)
        val streaky = findViewById<TextView>(R.id.textView7)
        val joker = findViewById<ImageView>(R.id.imageView1)
        val button = findViewById<Button>(R.id.button)
        val son = findViewById<TextView>(R.id.son)
        var skor = 0
        var time = 10
        var start = true

        // Kenny'i ekranda rastgele konumlara yerleştirmek için bir fonksiyon
        fun movejoker() {
            val random1 = 40..800
            val random2 = 450..2000
            val x = random1.random()
            val y = random2.random()
            joker.x = x.toFloat()
            joker.y = y.toFloat()
        }

        var streak = 0
        var lastActionTime = 0L
        val comboTimeLimit = 999// 0.9 saniye

        fun onAction() {
            val currentTime = System.currentTimeMillis()
            if (currentTime - lastActionTime <= comboTimeLimit) {
                if (streak < 5) {
                    streak += 1
                    skor += 1
                    skora.text = "+1"
                    streaky.setTextColor(Color.BLACK)
                    streaky.textSize = 26f
                }
                if (streak in 5..10) {
                    streak += 1
                    skor += 2
                    skora.text = "+3"
                    timea.text = "+1"
                    time += 1
                    streaky.setTextColor(Color.MAGENTA)
                    streaky.textSize = 30f
                }
                if (streak > 10) {
                    streak += 1
                    skor += 3
                    skora.text = "+3"
                    timea.text = "+2"
                    time += 2
                    streaky.setTextColor(Color.RED)
                    streaky.textSize = 34f
                }
                movejoker()

                skort.text = "$skor"
                streaky.text = "Streak: $streak"


            } else {
                // Combo sona erdi, comboCount'ı sıfırla
                streak = 0
                skora.text = ""
                timea.text = ""
                streaky.text = "Streak: $streak"
                streaky.setTextColor(Color.BLACK)
                streaky.textSize = 25f
            }
            lastActionTime = currentTime

        }

        button.setOnClickListener {
            button.visibility = View.GONE
            joker.setOnClickListener {
                if (time <= 0) {
                    time = 0
                    timet.text = "$time"
                    start = false
                    son.text = "Süre Bitti! Skorunuz: $skor "
                    son.visibility = View.VISIBLE
                    ObjectAnimator.ofFloat(son, "alpha", 0f, 1f).apply {
                        duration = 500
                        start()
                    }
                }
                if (start)
                    onAction()

            }

            // Her 1 saniyede bir Kenny'i hareket ettir
            GlobalScope.launch {
                while (start) {
                    if (streak < 5) {
                        delay(900) // 0.8 saniye bekle
                        movejoker()
                    }
                    if (streak >= 5) {
                        delay(800) // 0.6 saniye bekle
                        movejoker()
                    }
                    if (streak >= 10) {
                        delay(700) // 0.4 saniye bekle
                        movejoker()
                    }
                }
            }

            GlobalScope.launch {
                while (true) {
                    delay(800) // 0.8 saniye bekle
                    if (time > 0)
                        time -= 1

                    timet.text = "$time"
                }
            }
        }
    }
}
