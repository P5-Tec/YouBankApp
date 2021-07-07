package com.example.youbank.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.youbank.R
import com.example.youbank.databinding.LoginFragmentBinding
import com.example.youbank.viewModels.CustomerViewModel
import com.example.youbank.viewModels.LoginViewModel
import com.example.youbank.viewModels.SharedPreferenceViewModel

class LoginFragment: Fragment() {

    private var _binding: LoginFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var vm: LoginViewModel
    private val cvm: CustomerViewModel by activityViewModels()
    private val spvm: SharedPreferenceViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        _binding = LoginFragmentBinding.inflate(inflater, container, false)
        vm = ViewModelProvider(this).get(LoginViewModel::class.java)

        binding.loginBtn.setOnClickListener {

            vm.setData(
                binding.emailInput.text.toString(),
                binding.passwordInput.text.toString()
            )

            vm.login()

            vm.loginResponse.observe(viewLifecycleOwner, {
                if (it.isSuccessful) {

                    spvm.saveCustomerInSp(
                        it.body()!!.customerId,
                        it.body()!!.pincode,
                        it.body()!!.fullName)

                    cvm.addCustomerToRoomDB(it.body()!!.customerId)

                    //get data from api and save to room
                    vm.getAccounts(it.body()!!.customerId)
                    vm.getTransactions(it.body()!!.customerId)
                    vm.getCards(it.body()!!.customerId)

                    Toast.makeText(context, "Welcome, ${it.body()!!.fullName}", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_loginFragment_to_homeScreenMotionFragment)

                }
                else {
                    binding.passwordInput.text.clear()
                    Toast.makeText(
                        context, "Incorrect email or password, please check for any typos and try again!", Toast.LENGTH_LONG)
                        .show()
                }
            })

            //vm.login()

            //val c: Any = vm.doLogin()

            //Log.d("c", c.customerId.toString())

            //Log.d("c is a customer", (c is Customer).toString())

            //if (c is Customer) {
            //    Toast.makeText(context, "Successful login", Toast.LENGTH_SHORT).show()
            //
            //    Log.d("c id", c.customerId.toString())
            //    Log.d("c email", c.email)
            //    Log.d("c fn", c.fullName)
            //    Log.d("c pin", c.pincode)
            //
            //    spvm.saveCustomerInSp(c.customerId, c.pincode, c.fullName)
            //    cvm.addCustomerToRoomDB(c.customerId)
            //
            //    //get data from api and save to room
            //    vm.getAccounts()
            //    vm.getTransactions2(c.customerId)
            //    vm.getCards2()
            //
            //    findNavController().navigate(R.id.action_loginFragment_to_homeScreenMotionFragment)
            //}
            //else {
            //    Toast.makeText(this.context, "Incorrect email or password", Toast.LENGTH_SHORT).show()
            //    binding.passwordInput.text.clear()
            //}


            //navigate on bool, instead of observeing forever
            //vm.loggedin.observe(viewLifecycleOwner, {
            //    // TODO - Handle failed login error so it doesn't just crash
            //
            //    if (it.customerId != 0) {
            //        spvm.saveCustomerInSp(it.customerId, it.pincode, it.fullName)
            //        cvm.addCustomerToRoomDB(it.customerId)
            //
            //        //get data from api and save to room
            //        vm.getAccounts()
            //        vm.getTransactions2(it.customerId)
            //        vm.getCards2()
            //
            //        findNavController().navigate(R.id.action_loginFragment_to_homeScreenMotionFragment)
            //    }
            //    else {
            //        Toast.makeText(this.context, "Try again", Toast.LENGTH_SHORT).show()
            //        binding.passwordInput.text.clear()
            //    }
            //})
        }

        binding.backbtn.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragmentBackBtn)
        }

        return binding.root
    }

}