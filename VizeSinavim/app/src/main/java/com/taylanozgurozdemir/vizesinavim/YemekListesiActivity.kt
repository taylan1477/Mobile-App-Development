package com.taylanozgurozdemir.vizesinavim

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.taylanozgurozdemir.vizesinavim.databinding.ActivityYemekListesiBinding

class YemekListesiActivity : AppCompatActivity() {
    private lateinit var binding: ActivityYemekListesiBinding
    private val yemekListesi = mutableListOf(
        Yemek(
            "Maraş Dondurması",
            "Maraş dondurması, keçi sütü, salep ve şekerin karıştırılıp pişirilip çırpılarak dondurulmasıyla yapılır.",
            listOf("Keçi Sütü", "Sahlep", "Şeker")
        ),
        Yemek(
            "Un Sucuğu",
            "Tereyağında kavrulmuş una, şeker ve su karıştırılarak eklenip koyulaşana kadar pişirilir. Karışım kıvam alıp soğuduktan sonra dilimlenerek servis edilir. Tatlı, şerbetli ve yoğun kıvamlı bir tatlıdır.",
            listOf("2 su bardağı un", "1 su bardağı nişasta", "3 su bardağı su","Şerbet")
        ),
        Yemek(
            "Acem Pilavı",
            "Bulgurların tereyağı ve zeytinyağında kavrulup üzerine et suyu ve tuz eklenerek kısık ateşte pişirilmesiyle yapılır. İsteğe bağlı olarak içine nohut da eklenebilir. Pilav, suyu çekip yumuşadığında ocaktan alınarak demlenmeye bırakılır ve lezzetli bir şekilde servis edilir.",
            listOf("2 su bardağı pirinç", "3 su bardağı su", "Tereyağı", "Zeytin", "Baharatlar", "Havuç")
        )
    )
    private lateinit var adapter: YemekAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityYemekListesiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // RecyclerView ayarları
        adapter = YemekAdapter(yemekListesi) { secilenYemek ->
            // Yemek detay ekranına geçiş
            val intent = Intent(this, YemekDetayActivity::class.java)
            intent.putExtra("yemekIsim", secilenYemek.isim)
            intent.putExtra("yemekTarif", secilenYemek.tarif)
            intent.putStringArrayListExtra("yemekMalzemeler", ArrayList(secilenYemek.malzemeler))
            startActivity(intent)
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
    }

    // Menü oluşturma
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_yemek, menu)
        return true
    }

    // Menü öğesine tıklama
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_add_yemek -> {
                // Yemek ekleme ekranına geçiş
                val intent = Intent(this, YemekEkleActivity::class.java)
                startActivityForResult(intent, 100)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    // Yemek Ekleme Sonucunu Alma
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100 && resultCode == RESULT_OK) {
            val yeniYemek = data?.getSerializableExtra("yeniYemek") as? Yemek
            if (yeniYemek != null) {
                yemekListesi.add(yeniYemek)
                adapter.notifyDataSetChanged()
            }
        }
    }
}
