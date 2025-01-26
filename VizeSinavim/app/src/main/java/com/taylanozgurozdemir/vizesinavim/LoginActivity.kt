package com.taylanozgurozdemir.vizesinavim

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.taylanozgurozdemir.vizesinavim.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Giriş butonu tıklama işlemi
        binding.loginButton.setOnClickListener {
            val username = binding.username.text.toString()
            val password = binding.password.text.toString()

            // Kullanıcı adı ve şifre doğrulama
            if (username == "taylan" && password == "12345") {
                // Giriş başarılı -> YemekListesiActivity'ye yönlendir
                val intent = Intent(this, YemekListesiActivity::class.java)
                startActivity(intent)
                finish() // Login ekranını kapat
            }
        }
    }
}
