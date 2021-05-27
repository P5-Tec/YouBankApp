package com.example.youbank.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.youbank.R
import com.google.android.material.button.MaterialButton

class PasswordCreationFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_password_creation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.backbtn).setOnClickListener {
            findNavController().navigate(R.id.action_passwordCreationFragmentBackBtn)
        }

        val passwordInput: EditText = view.findViewById(R.id.passwordInput)
        val passwordConfirmInput: EditText = view.findViewById(R.id.passwordConfirmInput)
        val btnCreateAccount: MaterialButton = view.findViewById(R.id.btnCreateAccount)
        val warningLabel: TextView = view.findViewById(R.id.warningLabel)

        passwordInput.doOnTextChanged { _, _, _, _ ->
            if (passwordInput.length() == 4 && passwordConfirmInput.length() == 4) {
                if (passwordInput.text.toString() == passwordConfirmInput.text.toString()) {
                    warningLabel.visibility = View.INVISIBLE
                    btnCreateAccount.isEnabled = true
                }
                else {
                    warningLabel.visibility = View.VISIBLE
                    btnCreateAccount.isEnabled = false
                }
            }
            else{
                warningLabel.visibility = View.VISIBLE
                btnCreateAccount.isEnabled = false
            }
            passwordConfirmInput.doOnTextChanged { _, _, _, _ ->
                if (passwordInput.length() == 4 && passwordConfirmInput.length() == 4) {
                    if (passwordInput.text.toString() == passwordConfirmInput.text.toString()) {
                        warningLabel.visibility = View.INVISIBLE
                        btnCreateAccount.isEnabled = true
                    }
                    else {
                        warningLabel.visibility = View.VISIBLE
                        btnCreateAccount.isEnabled = false
                    }
                }
                else{
                    warningLabel.visibility = View.VISIBLE
                    btnCreateAccount.isEnabled = false
                }

            }
        }




    }
}