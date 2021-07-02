package com.example.youbank.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.youbank.databinding.TransactionItemBinding
import com.example.youbank.models.Transaction
import com.example.youbank.Util.DiffUtilTransactionCallback
import org.w3c.dom.Text

class TransactionAdapter :
    ListAdapter<Transaction, TransactionAdapter.TransactionViewHolder>(DiffUtilTransactionCallback()) {

    class TransactionViewHolder(private val binding: TransactionItemBinding) : RecyclerView.ViewHolder(binding.root){

        private val transactionPic: ImageView = binding.pic
        private val txtTransactionName: TextView = binding.txtTransactionName
        private val transactionValuta: TextView = binding.valutaIcon
        private val txtTransactionAmount: TextView = binding.txtTransactionAmount
        private val txtTransactionNote: TextView = binding.txtTransactionNote

        fun bind(data: Transaction){
            txtTransactionName.text = "Transaction"
            transactionValuta.text = "$"
            txtTransactionAmount.text = data.transactionAmount.toString()
            txtTransactionNote.text = "Hello world, im a note"
        }

        companion object{
            fun from(parent: ViewGroup): TransactionViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = TransactionItemBinding.inflate(layoutInflater, parent, false)
                return TransactionViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        return TransactionViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}