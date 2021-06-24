package com.example.youbank.Adapters


import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.example.youbank.models.Account
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.youbank.databinding.FragmentAccountBinding
import com.example.youbank.Util.DiffUtilAccountCallback

class AccountAdapter :
    ListAdapter<Account, AccountAdapter.AccountViewHolder>(DiffUtilAccountCallback()) {

    class AccountViewHolder(private val binding: FragmentAccountBinding) : RecyclerView.ViewHolder(binding.root){

        //private val accountPic: ImageView = binding.pic
        private val accountName: TextView = binding.txtAccountName
        private val accountNumber: TextView = binding.txtAccountNumber
        private val accountValuta: TextView = binding.valutaIcon
        private val accountBalance: TextView = binding.txtAccountBalance

        fun bind(data: Account){
            accountName.text = "Account"
            accountNumber.text = data.accountNumber.toString()
            accountValuta.text = "$"
            accountBalance.text = data.balance.toString()
        }

        companion object{
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
    }
}