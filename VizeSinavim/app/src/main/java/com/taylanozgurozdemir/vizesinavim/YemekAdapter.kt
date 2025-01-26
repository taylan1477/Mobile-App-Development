package com.taylanozgurozdemir.vizesinavim

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.taylanozgurozdemir.vizesinavim.databinding.ItemYemekBinding

class YemekAdapter(
    private val yemekListesi: List<Yemek>,
    private val onItemClick: (Yemek) -> Unit
) : RecyclerView.Adapter<YemekAdapter.YemekViewHolder>() {

    class YemekViewHolder(private val binding: ItemYemekBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(yemek: Yemek, onItemClick: (Yemek) -> Unit) {
            binding.textYemekAdi.text = yemek.isim


            binding.root.setOnClickListener {
                onItemClick(yemek)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): YemekViewHolder {
        val binding =
            ItemYemekBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return YemekViewHolder(binding)
    }

    override fun onBindViewHolder(holder: YemekViewHolder, position: Int) {
        holder.bind(yemekListesi[position], onItemClick)
    }

    override fun getItemCount(): Int = yemekListesi.size
}
