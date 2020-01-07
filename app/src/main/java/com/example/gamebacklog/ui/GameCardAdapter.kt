package com.example.gamebacklog.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gamebacklog.R
import com.example.gamebacklog.model.GameCard
import kotlinx.android.synthetic.main.activity_game_card.view.*
import java.time.format.DateTimeFormatter
import java.util.*

class GameCardAdapter(private val gameCards: List<GameCard>) : RecyclerView.Adapter<GameCardAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.activity_game_card, parent, false)
        )
    }

    /**
     * Returns the size of the list
     */
    override fun getItemCount(): Int {
        return gameCards.size
    }

    /**
     * Called by RecyclerView to display the data at the specified position.
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(gameCards[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(gameCard: GameCard) {
            itemView.tvTitle.setText(gameCard.title)
            itemView.tvPlatform.setText(gameCard.platform)
            itemView.tvDate.setText("Released: ${java.text.SimpleDateFormat("dd/MM/yyy").format(gameCard.date)}")

        }
    }
}
