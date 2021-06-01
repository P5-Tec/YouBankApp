package com.example.youbank.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.youbank.helpers.AccountCreationTextWatcher
import com.example.youbank.R
import com.example.youbank.databinding.FragmentAccountCreationBinding
import com.example.youbank.models.Customer
import com.example.youbank.viewModels.SharedViewModel
import java.util.*

class AccountCreationFragment: Fragment() {

    private var _binding: FragmentAccountCreationBinding? = null
    private val binding get() = _binding!!
    private lateinit var concatetenatedString: String
    private lateinit var c: Customer
    private val model: SharedViewModel by activityViewModels() // NEW WAY
    // private lateinit var sharedViewModel: AccountCreationViewModel // OLD WAY

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View { // Inflate the layout for this fragment
        _binding = FragmentAccountCreationBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.backbtn.setOnClickListener {
            findNavController().navigate(R.id.action_accountCreationBackBtn)
        }

        binding.btnCreatePassword.setOnClickListener {

            c = Customer()

            c.cpr = binding.cprInput.text.toString()
            c.fullName = binding.fullnameInput.text.toString().capitalizeWords()
            c.email = binding.emailInput.text.toString()
            c.phone = binding.phoneInput.text.toString()

            concatetenatedString =
                binding.addressInput.text.toString() + ", " + binding.postcodeInput.text.toString() + " " + binding.cityInput.text.toString()

            c.address = concatetenatedString

            model.setCustomer(c)

            findNavController().navigate(
                R.id.action_accountCreationFragment_to_passwordCreationFragment)
        }

        val edList = listOf(
            binding.cprInput, binding.fullnameInput, binding.emailInput, binding.phoneInput, binding.addressInput,
            binding.cityInput, binding.postcodeInput)

        val textWatcher = AccountCreationTextWatcher(edList, binding.btnCreatePassword)

        // All EditTexts will listen for the onTextChanged in MyTextWatcher
        for (editText: EditText in edList) {
            editText.addTextChangedListener(textWatcher)
        }
    }

    private fun String.capitalizeWords(): String = split(" ").joinToString(" ") { it.capitalize(Locale.ROOT) }
}