package com.example.youbank.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.youbank.R
import com.example.youbank.databinding.LoginFragmentBinding
import com.example.youbank.room.viewmodels.CustomerViewModel
import com.example.youbank.viewModels.LoginViewModel
import com.example.youbank.viewModels.SharedPreferenceViewModel

class LoginFragment: Fragment() {

    private var _binding: LoginFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var vm: LoginViewModel
    private val cvm: CustomerViewModel by activityViewModels()
    private val spvm: SharedPreferenceViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        _binding = LoginFragmentBinding.inflate(inflater, container, false)
        vm = ViewModelProvider(this).get(LoginViewModel::class.java)

        binding.loginBtn.setOnClickListener {
            vm.setData(
                binding.emailInput.text.toString(),
                binding.passwordInput.text.toString()
            )

            //navigate on bool, instead of observeing forever
            vm.loggedin.observe(viewLifecycleOwner, {
                if (it.customerId != 0) {
                    spvm.saveCustomerInSp(it.customerId, it.pincode.toString(), it.fullName)
                    cvm.addCustomerToRoomDB(it.customerId)

                    //get data from api and save to room
                    vm.getAccounts()
                    vm.getTransactions2(it.customerId)
                    //vm.getCards(it.customerId)
                    vm.getCards2()

                    findNavController().navigate(R.id.action_loginFragment_to_homeScreenMotionFragment)
                }
                else {
                    Toast.makeText(this.context, "Try again", Toast.LENGTH_SHORT).show()
                    binding.passwordInput.text.clear()
                }
            })
        }

        binding.backbtn.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragmentBackBtn)
        }

        return binding.root
    }

}