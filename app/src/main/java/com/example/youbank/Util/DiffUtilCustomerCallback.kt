package com.example.youbank.Util

import androidx.recyclerview.widget.DiffUtil
import com.example.youbank.models.Customer

class DiffUtilCustomerCallback : DiffUtil.ItemCallback<Customer>() {
    override fun areItemsTheSame(oldItem: Customer, newItem: Customer): Boolean {
        return oldItem.customerId == newItem.customerId
    }

    override fun areContentsTheSame(oldItem: Customer, newItem: Customer): Boolean {
        return oldItem == newItem
    }
}