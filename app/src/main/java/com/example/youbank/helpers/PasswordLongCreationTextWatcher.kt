package com.example.youbank.helpers

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class PasswordLongCreationTextWatcher(var passwordInput: EditText,
                                      var passwordConfirmInput: EditText,
                                      var btn: Button,
                                      var warningLabel: TextView
): TextWatcher {

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

        // Enabling button if passwords are not empty and they match
        // Showing warninglabel if one or more inputfields are empty
        if (passwordInput.length() > 0 && passwordConfirmInput.length() > 0) {
            if (passwordInput.text.toString() == passwordConfirmInput.text.toString()) { // Match found
                warningLabel.visibility = View.INVISIBLE
                btn.isEnabled = true
            }
            else { // No match found
                warningLabel.visibility = View.VISIBLE
                btn.isEnabled = false
            }
        }
        else {
            warningLabel.visibility = View.VISIBLE
            btn.isEnabled = false
        }

    }

    override fun afterTextChanged(s: Editable?) {}
}