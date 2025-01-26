package com.taylanozgurozdemir.birinciquizuygulamasi

import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, HomeFragment())
                .commit()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_home -> {
                // Ana Ekranı Göster
                showHomeFragment()
                return true
            }

            R.id.menu_login -> {
                // Giriş seçeneği
                showLoginFragment()
                return true
            }
            R.id.menu_red -> {
                // Kırmızı tema
                setThemeColor(Color.parseColor("#F08080"))
                return true
            }
            R.id.menu_blue -> {
                // Mavi tema
                setThemeColor(Color.parseColor("#ADD8E6"))
                return true
            }
            R.id.menu_yellow -> {
                // Sarı tema
                setThemeColor(Color.parseColor("#FFF8C6"))
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun showLoginFragment() {
        val fragment = LoginFragment() // Giriş ekranı için LoginFragment
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment) // fragment_container, ana yerleşimdeki FrameLayout'un ID'si
            .addToBackStack(null) // Geri tuşuna basıldığında önceki ekrana dönmek için
            .commit()
    }

    private fun showHomeFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, HomeFragment())
            .commit()
    }


    private fun setThemeColor(color: Int) {
        // Tema rengini değiştirmek için kod
        window.decorView.setBackgroundColor(color)
    }
}
