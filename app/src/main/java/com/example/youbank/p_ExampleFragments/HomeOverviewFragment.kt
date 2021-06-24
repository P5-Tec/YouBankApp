package com.example.youbank.p_ExampleFragments

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.youbank.databinding.FragmentHomeScreenMotionBinding
import com.example.youbank.Adapters.AccountAdapter
import com.example.youbank.Adapters.TransactionAdapter
//import com.example.youbank.viewModels.CustomerOverviewViewModel
//import com.example.youbank.viewModels.CustomerOverviewViewModelFactory

class HomeOverviewFragment : Fragment() {

    companion object {
        fun newInstance() = HomeOverviewFragment()
    }

    private var _binding : FragmentHomeScreenMotionBinding? = null
    private val binding get() = _binding!!

 //   private lateinit var viewModel: CustomerOverviewViewModel
 //   private lateinit var factory: CustomerOverviewViewModelFactory
    private lateinit var accountAdapter: AccountAdapter
    private lateinit var transactionAdapter: TransactionAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeScreenMotionBinding.inflate(inflater, container, false)

  //      factory = CustomerOverviewViewModelFactory(activity?.applicationContext as Application)
  //      viewModel = ViewModelProvider(this,factory).get(CustomerOverviewViewModel::class.java)

        accountAdapter = AccountAdapter()
  //      binding.accountList.adapter = accountAdapter
  //      binding.accountList.layoutManager = LinearLayoutManager(context)


        transactionAdapter = TransactionAdapter()
   //     binding.transactionList.adapter = transactionAdapter
   //     binding.transactionList.layoutManager = LinearLayoutManager(context)

        return binding?.root
        //return inflater.inflate(R.layout.customer_overview_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //viewModel = ViewModelProvider(this, defaultViewModelProviderFactory).get(CustomerOverviewViewModel::class.java)
        // TODO: Use the ViewModel

    //    viewModel.customerLive.observe(viewLifecycleOwner, Observer { it?.let { Log.i("CO+AC", it.toString()) } })
    //    viewModel.customerLive.observe(viewLifecycleOwner, Observer { it?.let { it.forEach {
    //    Log.i("Account:", it.accounts.toString())
    //    accountAdapter.submitList(it.accounts)}}})
    //    viewModel.transactionsLive.observe(viewLifecycleOwner,Observer {it?.let { it.forEach{ transactionAdapter.submitList(it.transactions)}}})
    }

}