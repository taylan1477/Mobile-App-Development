package com.taylanozgurozdemir.yemektariflerim

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class YemekDetayFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail_yemek, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val yemek = arguments?.getParcelable<Yemek>("yemek")

        if (yemek != null) {
            val yemekAdiTextView: TextView = view.findViewById(R.id.textYemekAdi)
            val yemekTarifiTextView: TextView = view.findViewById(R.id.textYemekTarifi)
            val yemekMalzemeleriTextView: TextView = view.findViewById(R.id.textYemekMalzemeleri)

            yemekAdiTextView.text = yemek.isim
            yemekTarifiTextView.text = yemek.tarif
            yemekMalzemeleriTextView.text = yemek.malzemeler
        }
    }
}