package com.example.youbank.fragments.accountFragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LiveData
import androidx.navigation.fragment.findNavController
import com.example.youbank.R
import com.example.youbank.databinding.FragmentAccountOverviewBinding
import com.example.youbank.models.AccountWithTransactions
import com.example.youbank.viewModels.AccountOverviewViewModel

class AccountOverviewFragment: Fragment() {

    private var _binding: FragmentAccountOverviewBinding? = null
    private val binding get() = _binding!!
    private val vm: AccountOverviewViewModel by activityViewModels()
    private var aId: Int? = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.getInt("data")
        aId = arguments?.getInt("data")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentAccountOverviewBinding.inflate(inflater, container, false)

        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        aId?.let { it ->
            vm.getAccount(it).observe(viewLifecycleOwner, { it ->
            it.forEach {
                binding.accountBalance.text = it.account.balance.toString()
                binding.accountNumber.text = it.account.accountNumber.toString()
                Log.i("hello","${it.transactions}")
            }
        })}

        binding.backbtn.setOnClickListener {
            findNavController().navigate(R.id.action_accountOverviewBackBtn)
        }

        binding.transactionButton.setOnClickListener {
            findNavController().navigate(R.id.action_accountOverviewFragment2_to_transferFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}