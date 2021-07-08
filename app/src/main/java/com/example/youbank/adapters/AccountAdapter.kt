package com.example.youbank.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.youbank.adapters.util.DiffUtilAccountCallback
import com.example.youbank.databinding.FragmentAccountBinding
import com.example.youbank.models.Account

class AccountAdapter(): ListAdapter<Account, AccountAdapter.AccountViewHolder>(DiffUtilAccountCallback()) {

    var onItemClick: ((Account) -> Unit)? = null

    class AccountViewHolder(val binding: FragmentAccountBinding) : RecyclerView.ViewHolder(binding.root) {

        private val accountName: TextView = binding.txtAccountName
        private val accountNumber: TextView = binding.txtAccountNumber
        private val accountValuta: TextView = binding.valutaIcon
        private val accountBalance: TextView = binding.txtAccountBalance


        fun bind(data: Account) {
            accountName.text = data.accountType.toString()
            accountNumber.text = data.accountNumber.toString()
            accountValuta.text = "$"
            accountBalance.text = data.balance.toString()

        }

        companion object {
            fun from(parent: ViewGroup): AccountViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = FragmentAccountBinding.inflate(layoutInflater, parent, false)
                return AccountViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountViewHolder {
        return AccountViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: AccountViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener {  onItemClick?.invoke(getItem(position))}
    }


}
