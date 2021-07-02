package com.example.youbank.fragments.buttomModals

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.youbank.R
import com.example.youbank.databinding.FragmentAccountSupportDialogBinding
import com.example.youbank.room.viewmodels.CustomerViewModel
import com.example.youbank.viewModels.SharedPreferenceViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class AccountSupportDialogFragment: BottomSheetDialogFragment() {

    private var _binding: FragmentAccountSupportDialogBinding? = null
    private val binding get() = _binding!!

    private val spvm: SharedPreferenceViewModel by activityViewModels()
    private val cvm: CustomerViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentAccountSupportDialogBinding.inflate(inflater, container, false)

        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.LogoutHeader.setOnClickListener {
            Toast.makeText(
                context, "Removing id: ${spvm.getCustomerIdInSp()}", Toast.LENGTH_LONG)
                .show()
            spvm.removeSpValue("customerId")
            cvm.readCustomer.observe(viewLifecycleOwner, {
                cvm.deleteCustomer(it)


            })
            findNavController().navigate(R.id.action_homeScreenMotionLogOut)
        }

        binding.SettingsHeader.setOnClickListener {
            findNavController().navigate(R.id.action_homeScreenMotionFragment_to_settingsFragment)
        }
    }

    companion object {
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            AccountSupportDialogFragment().apply { }
    }
}