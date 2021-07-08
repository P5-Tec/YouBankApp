package com.example.youbank.fragments.listFragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.youbank.R
import com.example.youbank.adapters.AccountAdapter
import com.example.youbank.viewModels.listViewModels.AccountListViewmodel

class AccountListFragment: Fragment() {

    private var columnCount = 1
    private lateinit var accountAdapter: AccountAdapter
    private val model: AccountListViewmodel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.account_item_list, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(
                        context,
                        LinearLayoutManager.VERTICAL,
                        false
                    )
                    else -> GridLayoutManager(context, columnCount)
                }
                accountAdapter = AccountAdapter()

                view.adapter = accountAdapter

                accountAdapter.onItemClick = { it ->
                    //parentFragmentManager.setFragmentResult("data", bundleOf("bundleKey" to "you did it"))
                    Log.i("accountResult", "logged, hopefully")
                    findNavController().navigate(R.id.action_homeScreenMotionFragment_to_accountOverviewFragment, bundleOf("data" to it.accountId))
                }

                model.allAccounts.observe(viewLifecycleOwner, { it?.let { accountAdapter.submitList(it) } })
            }
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}