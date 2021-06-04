package com.example.youbank.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.youbank.R
import com.example.youbank.databinding.FragmentGreeterBinding

class GreeterFragment: Fragment() {

    private var _binding: FragmentGreeterBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_greeter, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.createAccBtn.setOnClickListener {
            findNavController().navigate(R.id.action_greeterFragment_to_accountCreationFragment)
        }

        binding.loginBtn.setOnClickListener {
            findNavController().navigate(R.id.action_greeterFragment_to_keypadFragment)
        }
    }
}