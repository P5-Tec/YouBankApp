package com.example.youbank.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.youbank.R
import com.example.youbank.databinding.LoginFragmentBinding
import com.example.youbank.viewModels.CustomerViewModel
import com.example.youbank.viewModels.LoginViewModel
import com.example.youbank.viewModels.SharedPreferenceViewModel

class LoginFragment: Fragment() {

    private var _binding: LoginFragmentBinding? = null
    private val binding get() = _binding!!

    private val vm: LoginViewModel by activityViewModels()
    private val cvm: CustomerViewModel by activityViewModels()
    private val spvm: SharedPreferenceViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        _binding = LoginFragmentBinding.inflate(inflater, container, false)

        vm.loginResponse.observe(viewLifecycleOwner, {
            if (it.isSuccessful) {

                spvm.saveCustomerInSp(
                    it.body()!!.customerId,
                    it.body()!!.pincode,
                    it.body()!!.fullName)

                cvm.addCustomerToRoomDB(it.body()!!.customerId)

                //get data from api and save to room
                vm.getAccounts(it.body()!!.customerId)
                vm.getTransactions(it.body()!!.customerId)
                vm.getCards(it.body()!!.customerId)

                findNavController().navigate(R.id.action_loginFragment_to_homeScreenMotionFragment)
            }
            else {
                binding.passwordInput.text.clear()
                Toast.makeText(
                    context, "Incorrect email or password, please check for any typos and try again!", Toast.LENGTH_LONG)
                    .show()
            }
        })

        binding.loginBtn.setOnClickListener {

            // TODO - These 2 calls could be 1 call instead of 2
            vm.setData(
                binding.emailInput.text.toString(),
                binding.passwordInput.text.toString()
            )

            vm.login()
        }

        binding.backbtn.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragmentBackBtn)
        }

        return binding.root
    }

}