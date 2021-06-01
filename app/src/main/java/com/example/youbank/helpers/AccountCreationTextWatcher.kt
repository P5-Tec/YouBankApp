package com.example.youbank.helpers

import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText

class AccountCreationTextWatcher(var edList: List<EditText>, var btnCreatePassword: Button): TextWatcher {

    private var editTextCpr: EditText = edList[0]
    private var editTextFullname: EditText = edList[1]
    private var editTextEmail: EditText = edList[2]
    private var editTextPhone: EditText = edList[3]
    private var editTextAddress: EditText = edList[4]
    private var editTextCity: EditText = edList[5]
    private var editTextPostcode: EditText = edList[6]

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

        // buttons enable state is an expression that returns either true or false thus enabling or disabling it
        btnCreatePassword.isEnabled = (
        editTextCpr.length() == 10
        && editTextFullname.text.isNotBlank()
        && editTextEmail.text.toString().isValidEmail()
        && editTextPhone.length() == 8
        && editTextAddress.text.isNotBlank()
        && editTextCity.text.isNotBlank()
        && editTextPostcode.length() == 4)
    }

    override fun afterTextChanged(s: Editable?) {}

    // quick function to check for a valid email input - must follow the pattern <prefix>@<domain>
    private fun String.isValidEmail() =
        isNotBlank() && android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
}