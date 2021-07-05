package com.example.youbank.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.youbank.adapters.util.DiffUtilCardCallback
import com.example.youbank.databinding.CardItemBinding
import com.example.youbank.models.Card

class CardAdapter: ListAdapter<Card, CardAdapter.CardViewHolder>(DiffUtilCardCallback()) {

    class CardViewHolder(binding: CardItemBinding): RecyclerView.ViewHolder(binding.root) {

        private val cardCLayout: ConstraintLayout = binding.cardCLayout
        private val txtBalance: TextView = binding.txtBalance
        private val cardIcon: ImageView = binding.cardIcon
        private val txtCardTitle: TextView = binding.txtCardTitle
        private val txtCardNumber: TextView = binding.txtCardNumber

        fun bind(data: Card) {
            //TODO - make a getBalance function on account
            txtCardNumber.text = data.cardNumber.toString()
        }

        fun setWidth() {
            val params = itemView.layoutParams
            params.width = MATCH_PARENT
        }

        companion object {
            fun from(parent: ViewGroup): CardViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = CardItemBinding.inflate(layoutInflater, parent, false)
                return CardViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        return CardViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        //if item position is less than 2, set to match parent
        //only works with exactly 1 item in the list
        if (itemCount < 2) {
            holder.setWidth()
        }
        holder.bind(getItem(position))
    }
}