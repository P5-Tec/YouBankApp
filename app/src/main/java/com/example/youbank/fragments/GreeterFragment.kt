package com.example.youbank.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.youbank.R
import com.example.youbank.databinding.FragmentGreeterBinding
import com.example.youbank.viewModels.SharedPreferenceViewModel

class GreeterFragment: Fragment() {

    private var _binding: FragmentGreeterBinding? = null
    private val binding get() = _binding!!

    private val vm: SharedPreferenceViewModel by activityViewModels()
    private val spvm: SharedPreferenceViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //spvm.savePasswordInSp("1111")
        if (vm.getCustomerIdInSp() != 0) {
            Toast.makeText(
                context, "id: ${spvm.getCustomerIdInSp()} + password: ${spvm.getPasswordInSp().toString()}",
                Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_greeterFragment_to_keypadFragment)
        }
        else {
            Toast.makeText(context, "no id found in sp", Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentGreeterBinding.inflate(inflater, container, false)

        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.signUpBtn.setOnClickListener {
            findNavController().navigate(R.id.action_greeterFragment_to_accountCreationFragment)
        }

        binding.loginBtn.setOnClickListener {
            findNavController().navigate(R.id.action_greeterFragment_to_loginFragment)
        }
    }
}