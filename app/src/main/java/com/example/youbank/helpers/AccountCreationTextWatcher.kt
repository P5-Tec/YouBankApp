package com.example.youbank.helpers

import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View
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
    private var isLastCharHyphen: Boolean = false
    private var originalString = String()

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        if (s.toString() == editTextCpr.text.toString()) { // Are we in the CPR input field
            originalString = s.toString() // Save the string before change
            isLastCharHyphen = originalString.lastOrNull() == '-' // setting isLastCharHyphen
        }
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        // buttons enable state is an expression that returns either true or false thus enabling or disabling it
        btnCreatePassword.isEnabled = (
        editTextCpr.length() == 13 // Length must be 13 long
        && editTextFullname.text.isNotBlank() // Must not be empty and must contain some characters other than just whitespaces
        && editTextEmail.text.toString().isValidEmail() // Checking for valid email input
        && editTextAddress.text.isNotBlank()
        && editTextCity.text.isNotBlank()
        && editTextPostcode.length() == 4)
    }

    override fun afterTextChanged(s: Editable?) {
        if (s.toString() == editTextCpr.text.toString()) { // Are we in the CPR input field?
            val delimeter = "-"
            var newString: String = s.toString()
            // Removing listener so it doesn't fire again when i set the text manually
            editTextCpr.removeTextChangedListener(this)

            editTextCpr.setOnKeyListener(
                View.OnKeyListener { v, keyCode, event ->
                    // Backspace is pressed on on-screen keyboard(Not physical hardware)
                    // Is only true when releasing the key(KeyEvent.ACTION_UP) so that it doesn't fire twice
                    if (keyCode == KeyEvent.KEYCODE_DEL && event.action == KeyEvent.ACTION_UP) {
                        if (isLastCharHyphen) { // Last character was a hyphen before the backspace key was pressed
                            // The key press removed the hyphen so here im removing the last digit
                            // This is done like this otherwise a user would be able to break the format ##-##-##-####
                            val str: String = newString.substring(0, newString.length - 1)
                            editTextCpr.setText(str) // Here i set the text of the CPR input field
                            editTextCpr.setSelection(editTextCpr.length()) // Moving the cursor to the end of the field
                        }
                        else {
                            // This empty else is needed otherwise it will crash when the field is empty upon deleting
                        }

                        return@OnKeyListener true
                    }
                    // Runs when keys other than backspace is pressed(Not physical hardware, only on-screen keyboard)
                    // 0-9
                    else if (keyCode != KeyEvent.KEYCODE_DEL && event.action == KeyEvent.ACTION_UP) {
                        when (newString.length) { // Length is 2
                            2 -> {
                                newString += delimeter // Adding hyphen/delimeter
                                editTextCpr.setText(newString)
                                editTextCpr.setSelection(editTextCpr.length())
                            }
                            5 -> { // Length is 5
                                newString += delimeter
                                editTextCpr.setText(newString)
                                editTextCpr.setSelection(editTextCpr.length())
                            }
                            8 -> { // Length is 8
                                newString += delimeter
                                editTextCpr.setText(newString)
                                editTextCpr.setSelection(editTextCpr.length())
                            }
                        }
                    }

                    false
                })

            // Adding the listener again now that i'm done changing the text
            editTextCpr.addTextChangedListener(this)
        }
    }

    // quick function to check for a valid email input - must follow the pattern <prefix>@<domain>
    private fun String.isValidEmail() = isNotBlank() && android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
}