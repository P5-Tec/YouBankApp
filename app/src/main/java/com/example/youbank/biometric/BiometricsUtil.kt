package com.example.youbank.biometric

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.youbank.R


object BiometricsUtil {
    // TAG value
    private val TAG = "BioCheck"

    private fun hasBiometrics(context: Context): Int {
        val biometricManager = BiometricManager.from(context)
        return biometricManager.canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_STRONG)
    }

    fun isBiometricAvailable(context: Context): Boolean {
        return when (hasBiometrics(context)) {
            BiometricManager.BIOMETRIC_SUCCESS -> {
                Log.d(TAG, "isBioReady: Success"); true
            }
            BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> {
                Log.d(TAG, "isBioReady: Device user not enrolled"); false
            }
            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE -> {
                Log.d(TAG, "isBioReady: No biometrics hardware present"); false
            }
            BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE -> {
                Log.d(TAG, "isBioReady: Can't access hardware, right now"); false
            }
            else -> false
        }
    }

    // Returns a string depending on the biometrics error found, it is shown to the user in KeypadFragment
    fun biometricsErrorString(context: Context): String {
        return when (hasBiometrics(context)) {
            BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> "Biometrics not enrolled on device"
            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE -> "Biometrics hardware not found"
            BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE -> "Biometrics hardware unavailable"
            else -> "Check failed"
        }
    }


    // Biometrics prompt
    fun bioLogin(fragment: Fragment) {
        val executor = ContextCompat.getMainExecutor(fragment.context)
        val callback = object: BiometricPrompt.AuthenticationCallback() {
            override fun onAuthenticationError(
                errorCode: Int,
                errString: CharSequence
            ) {
                super.onAuthenticationError(errorCode, errString)
                Toast.makeText(
                    fragment.context,
                    "Authentication error: $errString", Toast.LENGTH_SHORT
                )
                    .show()
            }

            override fun onAuthenticationSucceeded(
                result: BiometricPrompt.AuthenticationResult
            ) {
                super.onAuthenticationSucceeded(result)
                fragment.view?.let {
                    Navigation.findNavController(it)
                        .navigate(R.id.action_keypadFragment_to_homeScreenMotionFragment)
                }
            }

            override fun onAuthenticationFailed() {
                super.onAuthenticationFailed()
                Toast.makeText(
                    fragment.context, "Authentication failed",
                    Toast.LENGTH_SHORT
                )
                    .show()
            }
        }

        val promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Biometric login for YouBank")
            .setSubtitle("Log in using your fingerprint")
            .setNegativeButtonText("Use account password")
            .build()

        return BiometricPrompt(fragment, executor, callback).authenticate(promptInfo)
    }
}