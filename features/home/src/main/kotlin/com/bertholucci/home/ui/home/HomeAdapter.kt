package com.bertholucci.home.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bertholucci.domain.model.Card
import com.bertholucci.home.R
import com.bertholucci.home.databinding.ItemHomeBinding
import com.bertholucci.home.extensions.loadFromUrl

class HomeAdapter(
    private val cards: List<Card>, private val onClick: (Card) -> Unit
) : RecyclerView.Adapter<HomeAdapter.CardViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        return CardViewHolder(layoutInflater(parent, R.layout.item_home))
    }

    private fun layoutInflater(parent: ViewGroup, layoutId: Int) =
        LayoutInflater.from(parent.context).inflate(layoutId, parent, false)


    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.bind(cards[position])
    }

    override fun getItemCount(): Int = cards.size

    inner class CardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding by lazy { ItemHomeBinding.bind(itemView) }

        fun bind(card: Card) {
            binding.ivPoster.loadFromUrl(card.image)
            binding.tvTitle.text = card.name
            binding.tvType.text = card.type
            binding.tvSet.text = card.cardSet
            binding.tvClass.text = card.playerClass

            binding.root.setOnClickListener {
                onClick.invoke(card)
            }
        }
    }
}
