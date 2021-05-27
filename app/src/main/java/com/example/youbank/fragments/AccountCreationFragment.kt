package com.example.youbank.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.youbank.R

class AccountCreationFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?):
            View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_account_creation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.backbtn).setOnClickListener {
            findNavController().navigate(R.id.action_accountCreationBackBtn)
        }

        view.findViewById<Button>(R.id.btnCreatePassword).setOnClickListener {
            findNavController().navigate(R.id.action_accountCreationFragment_to_passwordCreationFragment)
        }
    }
}