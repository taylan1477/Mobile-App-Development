package com.taylanozgurozdemir.kadrofutbol

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailPlayer : AppCompatActivity() {

    private lateinit var player: Player

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_player)

        // Intent'ten oyuncu bilgilerini al
        player = intent.getParcelableExtra("player")!!

        // Oyuncu bilgilerini göster
        displayPlayerDetails()
    }

    @SuppressLint("SetTextI18n")
    private fun displayPlayerDetails() {
        val playerNameTextView: TextView = findViewById(R.id.playerName)
        val playerAgeTextView: TextView = findViewById(R.id.playerAge)
        val playerPositionTextView: TextView = findViewById(R.id.playerPosition)
        val playerJerseyNumberTextView: TextView = findViewById(R.id.playerJerseyNumber)
        val playerMatchesPlayedTextView: TextView = findViewById(R.id.playerMatchesPlayed)
        val playerGoalsScoredTextView: TextView = findViewById(R.id.playerGoalsScored)
        val playerImageView: ImageView = findViewById(R.id.playerImage)

        playerNameTextView.text = player.name
        playerAgeTextView.text = "Yaş: ${player.age}"
        playerPositionTextView.text = "Mevki: ${player.position}"
        playerJerseyNumberTextView.text = "Forma Numarası: ${player.jerseyNumber}"
        playerMatchesPlayedTextView.text = "Oynadığı Maç Sayısı: ${player.matchesPlayed}"
        playerGoalsScoredTextView.text = "Gol Sayısı: ${player.goalsScored}"
        playerImageView.setImageResource(player.photoUrl) // Yerel bir resim kullanın
    }
}