package com.example.youbank.helpers

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class PasswordCreationTextWatcher(var passwordInput: EditText,
                                  var passwordConfirmInput: EditText,
                                  var btnCreateAccount: Button,
                                  var warningLabel: TextView
): TextWatcher {
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

        // Enabling button if passwords are both 4 long and they match
        // Showing warninglabel if passwords don't match

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
        else {
            warningLabel.visibility = View.VISIBLE
            btnCreateAccount.isEnabled = false
        }

    }

    override fun afterTextChanged(s: Editable?) {}
}