package com.example.youbank.Util
import androidx.recyclerview.widget.DiffUtil
import com.example.youbank.models.Card

class DiffUtilCardCallback : DiffUtil.ItemCallback<Card>() {
    override fun areItemsTheSame(oldItem: Card, newItem: Card): Boolean {
        return oldItem.cardId == newItem.cardId
    }

    override fun areContentsTheSame(oldItem: Card, newItem: Card): Boolean {
        return oldItem == newItem
    }

}