package com.taylanozgurozdemir.finalsinavim

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.taylanozgurozdemir.finalsinavim.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Firebase Auth başlat
        auth = FirebaseAuth.getInstance()

        // Giriş Yap butonu
        binding.loginButton1.setOnClickListener {
            val email = binding.username.text.toString().trim()
            val password = binding.password.text.toString().trim()

            if (validateInput(email, password)) {
                loginUser(email, password)
            }
        }

        // Kayıt Ol butonu
        binding.loginButton2.setOnClickListener {
            val email = binding.username.text.toString().trim()
            val password = binding.password.text.toString().trim()

            if (validateInput(email, password)) {
                registerUser(email, password)
            }
        }
    }

    private fun validateInput(email: String, password: String): Boolean {
        if (email.isEmpty()) {
            binding.username.error = "Email boş bırakılamaz!"
            binding.username.requestFocus()
            return false
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.username.error = "Geçerli bir email adresi girin!"
            binding.username.requestFocus()
            return false
        }

        if (password.isEmpty()) {
            binding.password.error = "Şifre boş bırakılamaz!"
            binding.password.requestFocus()
            return false
        }

        if (password.length < 6) {
            binding.password.error = "Şifre en az 6 karakter olmalı!"
            binding.password.requestFocus()
            return false
        }

        return true
    }

    private fun loginUser(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Giriş başarılı!", Toast.LENGTH_SHORT).show()
                    // Giriş başarılı -> EvListesiActivity'ye yönlendir
                    val intent = Intent(this, EvListesiActivity::class.java)
                    startActivity(intent)
                    finish() // Login ekranını kapat
                } else {
                    Toast.makeText(this, "Giriş başarısız: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun registerUser(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Kayıt başarılı!", Toast.LENGTH_SHORT).show()
                    // Giriş başarılı -> EvListesiActivity'ye yönlendir
                    val intent = Intent(this, EvListesiActivity::class.java)
                    startActivity(intent)
                    finish() // Login ekranını kapat
                } else {
                    Toast.makeText(this, "Kayıt başarısız: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }
}



