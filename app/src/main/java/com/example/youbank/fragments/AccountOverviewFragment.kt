package com.example.youbank.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.youbank.R
import com.example.youbank.databinding.FragmentAccountOverviewBinding
import com.example.youbank.fragments.viewmodels.AccountOverview_vm
import com.google.android.material.button.MaterialButton

class AccountOverviewFragment : Fragment() {
    private var _binding: FragmentAccountOverviewBinding? = null
    private val binding get() = _binding
    private lateinit var viewModel: AccountOverview_vm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAccountOverviewBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(AccountOverview_vm::class.java)
        viewModel.getAccountBalance().observe(viewLifecycleOwner, Observer<Int> { balance -> binding?.accountBalance?.text = balance.toString() })
        viewModel.getAccountNumber().observe(viewLifecycleOwner, Observer<String> { number -> binding?.accountNumber?.text = number.toString()})

        binding?.detailsBtn?.setOnClickListener{
            viewModel.setAccountBalance()
        }


        // Inflate the layout for this fragment
        return binding?.root
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