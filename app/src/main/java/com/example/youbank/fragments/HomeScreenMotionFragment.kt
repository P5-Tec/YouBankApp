package com.example.youbank.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.youbank.databinding.FragmentHomeScreenMotionBinding
import com.example.youbank.fragments.buttomModals.AccountSupportDialogFragment
import com.example.youbank.viewModels.CustomerViewModel
import com.example.youbank.viewModels.SharedPreferenceViewModel

class HomeScreenMotionFragment: Fragment() {

    private var _binding: FragmentHomeScreenMotionBinding? = null
    private val binding get() = _binding!!

    private val vm: CustomerViewModel by activityViewModels()
    private val spvm: SharedPreferenceViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHomeScreenMotionBinding.inflate(inflater, container, false)

        vm.readCustomer.observe(viewLifecycleOwner, {

            if (spvm.getCustomerIdInSp() == it.customerId) {
                //nothing changes
            }
            else {
                vm.nukeDB()
                // TODO - Refactor
                vm.addCustomerToRoomDB(spvm.getCustomerIdInSp()) //maybe change to logout ?
            }
        })

        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.profileImg.setOnClickListener {
            AccountSupportDialogFragment.newInstance().show(childFragmentManager, "dialog")
        }

        vm.readCustomer.observe(viewLifecycleOwner, { room ->
            if (room.fullName.isNotBlank()) {
                binding.header.text = room.fullName
            }
        })
    }

}