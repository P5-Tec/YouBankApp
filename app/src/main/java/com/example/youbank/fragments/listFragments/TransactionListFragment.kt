package com.example.youbank.fragments.listFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.youbank.R
import com.example.youbank.adapters.TransactionAdapter
import com.example.youbank.viewModels.listViewModels.TransactionListViewModel

class TransactionListFragment: Fragment() {

    private val model: TransactionListViewModel by activityViewModels()
    private lateinit var transactionAdapter: TransactionAdapter


    private var columnCount = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.transaction_item_list, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }

                transactionAdapter = TransactionAdapter()
                adapter = transactionAdapter
                model.allTransactions.observe(viewLifecycleOwner, { it?.let { transactionAdapter.submitList(it) } })
            }
        }
        return view
    }
}