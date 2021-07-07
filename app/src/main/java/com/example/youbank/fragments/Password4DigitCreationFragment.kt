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
import com.example.youbank.databinding.FragmentPassword4DigitCreationBinding
import com.example.youbank.helpers.Password4DigitCreationTextWatcher
import com.example.youbank.viewModels.SharedPreferenceViewModel

class Password4DigitCreationFragment: Fragment() {

    private var _binding: FragmentPassword4DigitCreationBinding? = null
    private val binding get() = _binding!!
    private val spvm: SharedPreferenceViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentPassword4DigitCreationBinding.inflate(inflater, container, false)

        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val textWatcher =
            Password4DigitCreationTextWatcher(
                binding.passwordInput, binding.passwordConfirmInput, binding.btnCreateAccount, binding.warningLabel)

        binding.passwordInput.addTextChangedListener(textWatcher)
        binding.passwordConfirmInput.addTextChangedListener(textWatcher)

        binding.backbtn.setOnClickListener {
            findNavController().navigate(R.id.action_password4DigitCreationFragmentBackBtn)
        }

        binding.btnCreateAccount.setOnClickListener {

            // TODO - Post customer here instead so we can post the 4 digit password too,
            //  or be lazy and make a seperate post with just the 4 digit password
            //  pest eller kolera

            Toast.makeText(context, "Password saved", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_password4DigitCreationFragment_to_greeterFragment)
        }

    }
}