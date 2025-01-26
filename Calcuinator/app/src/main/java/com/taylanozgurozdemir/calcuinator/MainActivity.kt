package com.taylanozgurozdemir.calcuinator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.annotation.SuppressLint
import android.graphics.Color
import android.text.Editable
import com.taylanozgurozdemir.calcuinator.databinding.ActivityMainBinding
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    @SuppressLint("SetTextI18n", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    fun sayisec(sayisecmeislemi: View) {
        val randomColor = Random.nextInt()
        sayisecmeislemi.setBackgroundColor(randomColor)
        if (yenioperator) {
            // Eğer yeni bir işlem başlatıldıysa, sonucu temizle
            binding.sonuc.text = Editable.Factory.getInstance().newEditable("")
            yenioperator = false // Yeni sayıya geçildi
        }

        var butonsec = sayisecmeislemi as Button
        val sonuc = binding.sonuc // burada sonuc TextView'i bağlanıyor
        var butondeger: String = sonuc.text.toString()

        when (butonsec.id) {
            binding.button18.id -> {
                butondeger += "0"
            }

            binding.button13.id -> {
                butondeger += "1"
            }

            binding.button14.id -> {
                butondeger += "2"
            }

            binding.button15.id -> {
                butondeger += "3"
            }

            binding.button9.id -> {
                butondeger += "4"
            }

            binding.button10.id -> {
                butondeger += "5"
            }

            binding.button11.id -> {
                butondeger += "6"
            }

            binding.button5.id -> {
                butondeger += "7"
            }

            binding.button6.id -> {
                butondeger += "8"
            }

            binding.button7.id -> {
                butondeger += "9"
            }
        }
        sonuc.text = Editable.Factory.getInstance().newEditable(butondeger)
    }

    var operator = ""
    var eskisayi = ""
    var yenioperator = true

    fun islem(islemsecmeislemi: View) {
        var islemsec = islemsecmeislemi as Button
        // Operatörü belirle
        when (islemsec.id) {
            binding.button8.id -> {
                operator = "*"
            }

            binding.button22.id -> {
                operator = "/"
            }

            binding.button16.id -> {
                operator = "+"
            }

            binding.button12.id -> {
                operator = "-"
            }
        }

        eskisayi = binding.sonuc.text.toString()
        yenioperator = true // Yeni işlem için bayrağı ayarla
    }

    fun esittir(esittir: View) {
        // Eğer yeni işlem başlatılmışsa, eski sayıyı ve operatörü güncellemeyin
        if (yenioperator) {
            return // Hiçbir işlem yapma
        }
        karart()

        var yenisayi = binding.sonuc.text.toString()
        if (eskisayi.isEmpty() || yenisayi.isEmpty()) {
            // Hata mesajı veya bir şeyler yapabilirsiniz
            return
        }

        var islemsonucu: Double? = null
        when (operator) {
            "*" -> {
                islemsonucu = eskisayi.toDouble() * yenisayi.toDouble()
            }

            "/" -> {
                if (yenisayi.toDouble() != 0.0) {
                    islemsonucu = eskisayi.toDouble() / yenisayi.toDouble()
                } else {
                    // Sıfıra bölme hatası
                    return
                }
            }

            "+" -> {
                islemsonucu = eskisayi.toDouble() + yenisayi.toDouble()
            }

            "-" -> {
                islemsonucu = eskisayi.toDouble() - yenisayi.toDouble()
            }
        }

        // Sonucu TextView'e yaz
        binding.sonuc.text = Editable.Factory.getInstance().newEditable(islemsonucu.toString())

        // İşlem tamamlandığında yeni işlem için hazır olun
        eskisayi = islemsonucu.toString() // Sonucu eski sayıya atayın
        yenioperator = true // Yeni işlem için bayrağı ayarla
    }

    fun ac(silmeislemi: View) {
        binding.sonuc.text = Editable.Factory.getInstance().newEditable("0")
        yenioperator = true
        karart()
        }

    fun sil(teksil: View) {
        binding.sonuc.text = Editable.Factory.getInstance().newEditable(binding.sonuc.text.dropLast(1))
        }

    fun isaretdegis(yeah: View) {
        val mevcutDeger = binding.sonuc.text.toString().toDouble()
        val yeniDeger = mevcutDeger * -1.0
        binding.sonuc.text = Editable.Factory.getInstance().newEditable(yeniDeger.toString())
        }

    fun karart(vararg sbuttons: Button) {
        val sbuttons = listOf(
            binding.button18,
            binding.button13,
            binding.button14,
            binding.button15,
            binding.button9,
            binding.button10,
            binding.button11,
            binding.button5,
            binding.button6,
            binding.button7
        )
        sbuttons.forEach { buttons ->
            buttons.setBackgroundColor(Color.BLACK)
        }
    }

    }

