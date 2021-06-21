package com.example.youbank.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.youbank.R
import com.example.youbank.databinding.Fragment4DigitPasswordCreationBinding
import com.example.youbank.helpers.PasswordCreationTextWatcher
import com.example.youbank.models.Customer
import com.example.youbank.retrofit.ApiService
import com.example.youbank.retrofit.CustomerService
import com.example.youbank.viewModels.SharedViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class `4DigitPasswordCreationFragment`: Fragment() {

    private var _binding: Fragment4DigitPasswordCreationBinding? = null
    private val binding get() = _binding!!
    private lateinit var newCustomer: Customer
    private val model: SharedViewModel by activityViewModels() // NEW WAY
    //private lateinit var sharedViewModel: AccountCreationViewModel // OLD WAY

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        _binding = Fragment4DigitPasswordCreationBinding.inflate(inflater, container, false)

        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val textWatcher =
            PasswordCreationTextWatcher(
                binding.passwordInput, binding.passwordConfirmInput, binding.btnCreateAccount, binding.warningLabel)

        binding.passwordInput.addTextChangedListener(textWatcher)
        binding.passwordConfirmInput.addTextChangedListener(textWatcher)

        model.getCustomer().observe(viewLifecycleOwner, { c ->

            newCustomer = Customer()
            newCustomer.birthday = c.birthday
            newCustomer.fullName = c.fullName
            newCustomer.email = c.email
            newCustomer.phone = c.phone
            newCustomer.address = c.address

            val txt: String = c.birthday + "\n" + c.fullName + "\n" + c.email + "\n" + c.phone + "\n" + c.address

            binding.description.text = txt
        })

        binding.backbtn.setOnClickListener {
            findNavController().navigate(R.id.action_passwordCreationFragmentBackBtn)
        }

        // TODO - put password into room
    }
}