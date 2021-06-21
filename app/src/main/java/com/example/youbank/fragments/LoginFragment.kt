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
import com.example.youbank.viewModels.LoginViewModel
import com.example.youbank.viewModels.SharedPreferenceViewModel

class LoginFragment: Fragment() {

    private var _binding: LoginFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var vm: LoginViewModel
    private val spvm: SharedPreferenceViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        _binding = LoginFragmentBinding.inflate(inflater, container, false)
        vm = ViewModelProvider(this).get(LoginViewModel::class.java)

        binding.loginBtn.setOnClickListener {
            vm.setEmail(binding.emailInput.text.toString())
            vm.setPass(binding.passwordInput.text.toString())

            vm.loggedin.observe(viewLifecycleOwner, { x ->
                if (x.customerId != 0) {
                    Toast.makeText(this.context, "Successful login", Toast.LENGTH_SHORT).show()
                    spvm.saveCustomerIdInSp(x.customerId)
                    spvm.saveNameInSp(x.fullName)
                    findNavController().navigate(R.id.action_loginFragment_to_homeScreenMotionFragment)
                }
                else {
                    Toast.makeText(this.context, "Incorrect", Toast.LENGTH_SHORT).show()
                    binding.passwordInput.setText("")
                }
            })
        }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        vm = ViewModelProvider(this).get(LoginViewModel::class.java)
    }

}