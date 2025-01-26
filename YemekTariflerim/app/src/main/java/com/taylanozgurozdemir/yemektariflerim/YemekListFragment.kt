package com.taylanozgurozdemir.yemektariflerim

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class YemekListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: YemekAdapter

    // Örnek yemek verileri
    val yemekler = mutableListOf(
        Yemek("Maraş Dondurması", "Maraş dondurması, keçi sütü, salep ve şekerin karıştırılıp pişirilip çırpılarak dondurulmasıyla yapılır.", "Keçi Sütü, Sahlep, Şeker"),
        Yemek("Un Sucuğu", "Tereyağında kavrulmuş una, şeker ve su karıştırılarak eklenip koyulaşana kadar pişirilir. Karışım kıvam alıp soğuduktan sonra dilimlenerek servis edilir. Tatlı, şerbetli ve yoğun kıvamlı bir tatlıdır.", "Un, Nişasta, Şerbet"),
        Yemek("Acem Pilavı", "Acem pilavı, bulgurların tereyağı ve zeytinyağında kavrulup üzerine et suyu ve tuz eklenerek kısık ateşte pişirilmesiyle yapılır. İsteğe bağlı olarak içine nohut da eklenebilir. Pilav, suyu çekip yumuşadığında ocaktan alınarak demlenmeye bırakılır ve lezzetli bir şekilde servis edilir.", "Pilav, Kıyma, Havuç, Soğan")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.recycler_view, container, false) // Doğru layout dosyasını kullanın
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        adapter = YemekAdapter(yemekler) { yemek ->
            showYemekDetails(yemek)
        }

        recyclerView.adapter = adapter

        // Menü oluşturulmasına izin ver
        setHasOptionsMenu(true)
    }

    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_yemek, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_add_yemek -> {
                // Yeni yemek ekleme fragment'ına geçiş yap
                showAddYemekFragment()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showAddYemekFragment() {
        val fragment = YemekEkleFragment()

        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment) // fragment_container yerine uygun ID'yi kullanın
            .addToBackStack(null) // Geri dönüş için yığın ekle
            .commit()
    }

    private fun showYemekDetails(yemek: Yemek) {
        val fragment = YemekDetayFragment()

        val bundle = Bundle()
        bundle.putParcelable("yemek", yemek)
        fragment.arguments = bundle

        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addYemekToList(yeniYemek: Yemek) {
        yemekler.add(yeniYemek) // Yeni yemeği listeye ekleyin.
        adapter.notifyDataSetChanged() // Adapter'ı güncelleyin.
    }
}