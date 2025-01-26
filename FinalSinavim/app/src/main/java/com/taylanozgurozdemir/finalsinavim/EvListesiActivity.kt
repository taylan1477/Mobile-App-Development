package com.taylanozgurozdemir.finalsinavim

import Ev
import EvAdapter
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.taylanozgurozdemir.finalsinavim.databinding.ActivityEvListesiBinding

class EvListesiActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEvListesiBinding
    private lateinit var evAdapter: EvAdapter
    private val evListesi = mutableListOf<Ev>()
    private val firestore = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance() // FirebaseAuth örneği

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEvListesiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        displayUserEmail() // Kullanıcı e-postasını göster
        fetchEvListesi()
    }

    private fun setupRecyclerView() {
        evAdapter = EvAdapter(evListesi)
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@EvListesiActivity)
            adapter = evAdapter
        }
    }

    @SuppressLint("SetTextI18n")
    private fun displayUserEmail() {
        val currentUser = auth.currentUser
        val email = currentUser?.email ?: "Bilinmeyen Kullanıcı"
        val username = email.substringBefore("@") // "@" öncesindeki kısmı al
        val loginnickTextView = findViewById<TextView>(R.id.loginnick)
        loginnickTextView.text = "Hoşgeldiniz, $username"
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun fetchEvListesi() {
        firestore.collection("houses")
            .get()
            .addOnSuccessListener { documents ->
                evListesi.clear()
                for (document in documents) {
                    val house = document.toObject(Ev::class.java)
                    evListesi.add(house)
                }
                evAdapter.notifyDataSetChanged()
            }
            .addOnFailureListener { e ->
                Log.e("EvListesiActivity", "Hata: ${e.message}", e)
            }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_ev, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_add_ev -> {
                val intent = Intent(this, EvEkleActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.action_quit -> {
                // Kullanıcı oturumunu kapat
                FirebaseAuth.getInstance().signOut()

                // Giriş ekranına yönlendir
                val intent = Intent(this, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onResume() {
        super.onResume()
        fetchEvListesi() // Yeni ev eklenirse listeyi güncelle
    }
}
