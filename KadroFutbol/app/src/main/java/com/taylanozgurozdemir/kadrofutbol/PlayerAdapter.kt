package com.taylanozgurozdemir.kadrofutbol

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PlayerAdapter(private val players: List<Player>, private val onClick: (Player) -> Unit) : RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder>() {

    class PlayerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val playerImage: ImageView = itemView.findViewById(R.id.playerImage)
        val playerName: TextView = itemView.findViewById(R.id.playerName)
        val playerPosition: TextView = itemView.findViewById(R.id.playerPosition)
        val playerJerseyNumber: TextView = itemView.findViewById(R.id.playerJerseyNumber)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_oyuncu, parent, false)
        return PlayerViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        val player = players[position]
        holder.playerName.text = player.name
        holder.playerPosition.text = player.position
        holder.playerJerseyNumber.text = "#${player.jerseyNumber}"
        holder.playerImage.setImageResource(player.photoUrl)
        holder.itemView.setOnClickListener { onClick(player) }
    }

    override fun getItemCount(): Int = players.size
}

