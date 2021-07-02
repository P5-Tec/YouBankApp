package com.example.youbank.helpers

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import com.example.youbank.viewModels.CustomerViewModel

class SettingsTextWatcher(edList: List<EditText>, private val vm: CustomerViewModel): TextWatcher {

    private var editTextFullname: EditText = edList[0]
    private var editTextEmail: EditText = edList[1]
    private var editTextPhone: EditText = edList[2]
    private var editTextAddress: EditText = edList[3]
    private var editTextCity: EditText = edList[4]
    private var editTextPostcode: EditText = edList[5]

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

        if (editTextFullname.text.toString().isNotBlank() &&
            editTextEmail.text.toString().isValidEmail() &&
            editTextPhone.text.toString().isNotBlank() && editTextPhone.length() == 8 && !editTextPhone.text.contains(" ") &&
            editTextAddress.text.toString().isNotBlank() &&
            editTextCity.text.toString().isNotBlank() &&
            editTextPostcode.length() == 4
        ) {
            vm.setIsSettingsValid(true)
        }
        else {
            vm.setIsSettingsValid(false)
        }
    }

    override fun afterTextChanged(s: Editable?) {
    }

    // quick function to check for a valid email input - must follow the pattern <prefix>@<domain>
    private fun String.isValidEmail() = isNotBlank() && android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
}