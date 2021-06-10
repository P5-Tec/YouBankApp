package com.example.youbank

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.youbank.databinding.LoginFragmentBinding
import com.example.youbank.viewModels.LoginViewModel

class LoginFragment: Fragment() {

    private var _binding: LoginFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        _binding = LoginFragmentBinding.inflate(inflater, container, false)

        binding.loginBtn.setOnClickListener {
            viewModel.setEmail(binding.emailInput.text.toString())
            viewModel.setPass(binding.passwordInput.text.toString())

            viewModel.loggedin.observe(viewLifecycleOwner, { tst ->
                if (tst.customerId != null) {
                    findNavController().navigate(R.id.action_loginFragment_to_homeScreenMotionFragment)
                }
            })
        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        // TODO: Use the ViewModel
    }

}