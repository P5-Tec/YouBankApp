package com.example.youbank.util

import androidx.recyclerview.widget.DiffUtil
import com.example.youbank.models.Transaction

class DiffUtilTransactionCallback: DiffUtil.ItemCallback<Transaction>() {
    
    override fun areItemsTheSame(oldItem: Transaction, newItem: Transaction): Boolean {
        return oldItem.transactionId == newItem.transactionId
    }

    override fun areContentsTheSame(oldItem: Transaction, newItem: Transaction): Boolean {
        return oldItem == newItem
    }

}