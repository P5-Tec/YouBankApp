package com.example.youbank

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.youbank.databinding.LoginFragmentBinding
import com.example.youbank.models.Customer
import com.example.youbank.viewModels.KeypadViewModel
import com.example.youbank.viewModels.LoginViewModel

class LoginFragment : Fragment() {

    private var _binding: LoginFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        _binding = LoginFragmentBinding.inflate(inflater, container, false)

        binding.loginBtn.setOnClickListener{
            viewModel.setEmail(binding.emailInput.text.toString())
            viewModel.setPass(binding.passwordInput.text.toString())

            viewModel.loggedin.observe(viewLifecycleOwner, { tst ->
                if (tst.customerId != null){
                    findNavController().navigate(R.id.action_loginFragment_to_homeScreenFragment)
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