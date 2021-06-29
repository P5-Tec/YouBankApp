package com.example.youbank.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.youbank.R
import com.example.youbank.databinding.FragmentAccountOverviewBinding
import com.example.youbank.viewModels.AccountOverviewViewModel
import com.google.android.material.button.MaterialButton

class AccountOverviewFragment: Fragment() {
    private var _binding: FragmentAccountOverviewBinding? = null
    private val binding get() = _binding!!

    private val vm: AccountOverviewViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentAccountOverviewBinding.inflate(inflater, container, false)

        vm.readAccount.observe(viewLifecycleOwner, {
            Log.d("account details", it.balance.toString())
            Log.d("account details", it.accountNumber.toString())
            Log.d("account details", it.accountType.toString())
            Log.d("account details", it.customerId.toString())

            binding.accountBalance.text = it.balance.toString()
        })

        vm.readAccount.observe(viewLifecycleOwner, {
            binding.accountNumber.text = it.accountNumber.toString()
        })

        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<MaterialButton>(R.id.backbtn).setOnClickListener {
            findNavController().navigate(R.id.action_accountOverviewBackBtn)
        }

        view.findViewById<MaterialButton>(R.id.transaction_button).setOnClickListener {
            findNavController().navigate(R.id.action_accountOverviewFragment2_to_transferFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}