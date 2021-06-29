package com.example.youbank.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.youbank.Util.DiffUtilCardCallback
import com.example.youbank.databinding.CardItemBinding
import com.example.youbank.databinding.FragmentHomeScreenMotionBinding
import com.example.youbank.fragments.items.CardItemFragment
import com.example.youbank.models.Card
import org.w3c.dom.Text

class CardAdapter : ListAdapter<Card,CardAdapter.CardViewHolder>(DiffUtilCardCallback()) {

    class CardViewHolder(private val binding: CardItemBinding) : RecyclerView.ViewHolder(binding.root){

        private val txtBalance: TextView = binding.txtBalance
        private val cardIcon: ImageView = binding.cardIcon
        private val txtCardTitle: TextView = binding.txtCardTitle
        private val txtCardNumber: TextView = binding.txtCardNumber

        fun bind(data: Card){
            //TODO - make a getBalance function on account
            //txtBalance.text =
            txtCardNumber.text = data.cardNumber.toString()
        }

        companion object{
            fun from(parent: ViewGroup): CardViewHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = CardItemBinding.inflate(layoutInflater,parent,false)
                return CardViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardAdapter.CardViewHolder {
        return CardViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: CardAdapter.CardViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}