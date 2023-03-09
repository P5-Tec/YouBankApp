package com.example.youbank.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.youbank.R
import com.example.youbank.databinding.FragmentPasswordLongCreationBinding
import com.example.youbank.helpers.PasswordLongCreationTextWatcher
import com.example.youbank.models.Customer
import com.example.youbank.retrofit.ApiService
import com.example.youbank.retrofit.CustomerService
import com.example.youbank.viewModels.SharedPreferenceViewModel
import com.example.youbank.viewModels.SharedViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PasswordLongCreationFragment: Fragment() {

    private var _binding: FragmentPasswordLongCreationBinding? = null
    private val binding get() = _binding!!

    private val vm: SharedViewModel by activityViewModels()
    private val spvm: SharedPreferenceViewModel by activityViewModels()
    private lateinit var newCustomer: Customer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentPasswordLongCreationBinding.inflate(inflater, container, false)

        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vm.getCustomer().observe(viewLifecycleOwner, { c ->
            newCustomer = c
            val txt: String =
                newCustomer.birthday + "\n" + newCustomer.fullName + "\n" + newCustomer.email + "\n" + newCustomer.phone + "\n" + newCustomer.address

            binding.description.text = txt
        })

        val textWatcher =
            PasswordLongCreationTextWatcher(
                binding.passwordInput, binding.passwordConfirmInput, binding.btnNext, binding.warningLabel)

        binding.passwordInput.addTextChangedListener(textWatcher)
        binding.passwordConfirmInput.addTextChangedListener(textWatcher)

        binding.backbtn.setOnClickListener {
            findNavController().navigate(R.id.action_passwordLongCreationFragmentBackBtn)
        }

        binding.btnNext.setOnClickListener {
            // TODO - Refactor methods, maybe use https://github.com/NyCodeGHG/bcrypt/commits/main instead

            // Hashing password and saving it in viewmodel
            vm.hashPassword(binding.passwordInput.text.toString())
            Log.d("hash pass", vm.getPasswordHash())
            // Assigning the hash to the customer object
            vm.setPassword()

            // Get the customer from viewmodel then post it with api to database
            vm.getCustomer().observe(viewLifecycleOwner, { c ->
                val service: CustomerService = ApiService.buildService(CustomerService::class.java)
                val req: Call<Void> = service.addNewCustomer(c)

                try {
                    req.enqueue(object: Callback<Void> {
                        override fun onResponse(call: Call<Void>, response: Response<Void>) {
                            Log.d("response", response.toString())
                            if (response.isSuccessful) {

                                newCustomer.customerId = it.id

                                vm.setCustomer(newCustomer)

                                Toast.makeText(context, "Successfully Created - Congratulations!!", Toast.LENGTH_LONG).show()
                                findNavController().navigate(
                                    R.id.action_passwordLongCreationFragment_to_password4DigitCreationFragment)
                            }
                            else {
                                Toast.makeText(
                                    context, "Please try again, something went wrong - ${response.code()}",
                                    Toast.LENGTH_LONG).show()
                            }
                        }

                        override fun onFailure(call: Call<Void>, t: Throwable) {
                            Log.d("Post Failed: ", t.message.toString())
                        }
                    })
                } catch (e: Exception) {
                    Log.d("Catch: ", e.message.toString())
                }
            })

        }

    }
}