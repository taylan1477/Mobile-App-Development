package com.taylanozgurozdemir.yemektariflerim

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class YemekAdapter(
    private val yemekler: List<Yemek>, // List<Yemek> türünde olmalı
    private val onClick: (Yemek) -> Unit
) : RecyclerView.Adapter<YemekAdapter.YemekViewHolder>() {

    class YemekViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val yemekIsim: TextView = itemView.findViewById(R.id.textYemekAdi)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): YemekViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_yemek, parent, false)
        return YemekViewHolder(view)
    }

    override fun onBindViewHolder(holder: YemekViewHolder, position: Int) {
        val yemek = yemekler[position]

        holder.yemekIsim.text = yemek.isim

        holder.itemView.setOnClickListener { onClick(yemek) }
    }

    override fun getItemCount(): Int = yemekler.size
}