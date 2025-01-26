package com.taylanozgurozdemir.yemektariflerim

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment

class YemekEkleFragment : Fragment() {

    // Arayüz tanımı
    interface OnYemekEkleListener {
        fun onYemekEkle(yeniYemek: Yemek)
    }

    private lateinit var isimEditText: EditText
    private lateinit var tarifEditText: EditText
    private lateinit var malzemelerEditText: EditText
    private lateinit var listener: OnYemekEkleListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as OnYemekEkleListener
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_yemek_ekle, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        isimEditText = view.findViewById(R.id.isimEditText)
        tarifEditText = view.findViewById(R.id.tarifEditText)
        malzemelerEditText = view.findViewById(R.id.malzemelerEditText)

        val saveButton: Button = view.findViewById(R.id.saveButton)

        saveButton.setOnClickListener {
            val isim = isimEditText.text.toString()
            val tarif = tarifEditText.text.toString()
            val malzemeler = malzemelerEditText.text.toString()

            // Yeni yemek nesnesini oluşturun ve dinleyiciye iletin.
            val yeniYemek = Yemek(isim, tarif, malzemeler)
            listener.onYemekEkle(yeniYemek) // Yeni yemeği dinleyiciye iletin

            // Geri dönmek için fragment'ı kapatın.
            requireActivity().supportFragmentManager.popBackStack()
        }
    }
}