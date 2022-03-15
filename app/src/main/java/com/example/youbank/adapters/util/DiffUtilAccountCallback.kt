package com.example.youbank.adapters.util

import androidx.recyclerview.widget.DiffUtil
import com.example.youbank.models.Account

class DiffUtilAccountCallback: DiffUtil.ItemCallback<Account>() {

    override fun areItemsTheSame(oldItem: Account, newItem: Account): Boolean {
        return oldItem.accountId == newItem.accountId
    }

    override fun areContentsTheSame(oldItem: Account, newItem: Account): Boolean {
        return oldItem == newItem
    }

}