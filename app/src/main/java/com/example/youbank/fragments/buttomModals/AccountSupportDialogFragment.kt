package com.example.youbank.fragments.buttomModals

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.youbank.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class AccountSupportDialogFragment : BottomSheetDialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_account_support_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val logoutHeader: TextView = view.findViewById(R.id.LogoutHeader)
        logoutHeader.setOnClickListener{
            findNavController().navigate(R.id.action_homeScreenFragmentLogOut)
        }
    }

    companion object {

        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            AccountSupportDialogFragment().apply { }
    }
}