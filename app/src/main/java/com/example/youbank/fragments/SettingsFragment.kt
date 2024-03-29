package com.example.youbank.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.youbank.R
import com.example.youbank.databinding.FragmentSettingsBinding
import com.example.youbank.helpers.SettingsTextWatcher
import com.example.youbank.models.Customer
import com.example.youbank.viewModels.CustomerViewModel
import com.example.youbank.viewModels.SharedPreferenceViewModel

class SettingsFragment: Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    private val vm: CustomerViewModel by activityViewModels()
    private val spvm: SharedPreferenceViewModel by activityViewModels()
    private lateinit var updatedCustomer: Customer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Overriding the backpress
        val callback: OnBackPressedCallback = object: OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // Navigates to homescreen instead of bottomModal
                findNavController().navigate(R.id.action_settingsFragmentBackBtn)
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
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

            // Putting the strings into the inputfields in the view
            binding.addressInput.setText(substrings[0])
            binding.postcodeInput.setText(substrings[1])
            binding.cityInput.setText(substrings[2])

            // Getting biometricUseStatus from shared preferences
            binding.biometricSwitch.isChecked = spvm.getBiometricUseStatus()
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

    // Saving data in room
    // TODO - Send data to api, maybe use livedata in room
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

            spvm.saveBiometricUseStatus(binding.biometricSwitch.isChecked)

            Toast.makeText(context, "Settings were saved", Toast.LENGTH_SHORT).show()
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