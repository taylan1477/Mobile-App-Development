package com.taylanozgurozdemir.kadrofutbol

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    val players = listOf(
        Player("Furkan Bayır", 25, "Kaleci", 1, 12, 0, R.drawable.deniz_donmezer),
        Player("Samet Akaydin", 28, "Defans", 3, 12, 1, R.drawable.samet_akaydin),
        Player("Kaan Kanak", 27, "Defans", 14, 12, 0, R.drawable.kaan_kanak),
        Player("Andreaw Gravillon", 26, "Defans", 5, 8, 2, R.drawable.andreaw_gravillon),
        Player("Semih Güler", 29, "Defans", 4, 11, 1, R.drawable.semih_guler),
        Player("Tayfun Aydoğan", 28, "Orta Saha", 8, 9, 0, R.drawable.tayfun_aydogan),
        Player("Yusuf Erdoğan", 27, "Orta Saha", 10, 12, 2, R.drawable.yusuf_erdogan),
        Player("Yusuf Sarı", 25, "Orta Saha", 7, 9, 0, R.drawable.yusuf_sari),
        Player("Emre Akbaba", 31, "Forvet", 9, 12, 4, R.drawable.emre_akbaba),
        Player("Mario Balotelli", 34, "Forvet", 45, 12, 5, R.drawable.mario_balotelli),
        Player("Gökhan İnler", 38, "Forvet", 7, 12, 6, R.drawable.gokhan_inler)
    )

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: PlayerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = PlayerAdapter(players) { player ->
            showPlayerDetails(player)
        }

        recyclerView.adapter = adapter
    }

    private fun showPlayerDetails(player: Player) {
        val intent = Intent(this, DetailPlayer::class.java)
        intent.putExtra("player", player) // Oyuncu nesnesini intent'e ekle
        startActivity(intent) // Yeni aktiviteyi başlat
    }
}


