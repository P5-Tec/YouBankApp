package com.example.youbank.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.youbank.R
import com.example.youbank.databinding.FragmentSettingsBinding
import com.example.youbank.helpers.SettingsTextWatcher
import com.example.youbank.models.Customer
import com.example.youbank.room.viewmodels.CustomerViewModel

class SettingsFragment: Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    private val vm: CustomerViewModel by activityViewModels()
    private lateinit var updatedCustomer: Customer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)

        // Reading data from room and putting it into the view
        vm.readCustomer.observe(viewLifecycleOwner, { roomdata ->

            updatedCustomer = roomdata

            binding.fullnameInput.setText(updatedCustomer.fullName)
            binding.emailInput.setText(updatedCustomer.email)
            binding.phoneInput.setText(updatedCustomer.phone)


            // Splitting the address into 3 seperate strings
            val substrings = updatedCustomer.address.split(", ")

            Log.d("strings", substrings.toString())

            // Putting the strings into the inputfields in the view
            binding.addressInput.setText(substrings[0])
            binding.postcodeInput.setText(substrings[1])
            binding.cityInput.setText(substrings[2])
        })
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Getting the customerId from room
        vm.readCustomer.observe(viewLifecycleOwner, { room ->
            updatedCustomer.customerId = room.customerId
            updatedCustomer.birthday = room.birthday
        })

        // onClickListerner for back button
        binding.backbtn.setOnClickListener {
            findNavController().navigate(R.id.action_settingsFragmentBackBtn)
        }

        // List of all the EditTexts in the settings view
        val edList = listOf(
            binding.fullnameInput, binding.emailInput, binding.phoneInput, binding.addressInput,
            binding.cityInput, binding.postcodeInput)

        // Initializing my textwatcher and passing it my list of edittexts and viewmodel
        val textWatcher = SettingsTextWatcher(edList, vm)

        // All EditTexts will listen for the functions in SettingsTextWatcher
        for (editText: EditText in edList) {
            editText.addTextChangedListener(textWatcher)
        }

    }

    override fun onDestroy() {
        super.onDestroy()

        if (vm.getIsSettingsValid()) {
            val concatetenatedString =
                binding.addressInput.text.toString().capitalizeWords() +
                ", " + binding.postcodeInput.text.toString() +
                ", " + binding.cityInput.text.toString().capitalizeWords()

            updatedCustomer.fullName = binding.fullnameInput.text.toString().capitalizeWords()
            updatedCustomer.phone = binding.phoneInput.text.toString()
            updatedCustomer.address = concatetenatedString
            updatedCustomer.email = binding.emailInput.text.toString()

            vm.updateCustomerInRoom(updatedCustomer)

            Toast.makeText(context, "Your settings were saved", Toast.LENGTH_LONG).show()
        }
        else {
            Toast.makeText(context, "Invalid input", Toast.LENGTH_LONG).show()
        }
    }

    // Method to capitalize the first character of every word in a string
    private fun String.capitalizeWords(): String =
        split(" ").joinToString(" ") { fc ->
            fc.replaceFirstChar { uc ->
                uc.uppercaseChar()
            }
        }
}