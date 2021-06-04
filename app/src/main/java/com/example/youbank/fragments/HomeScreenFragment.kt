package com.example.youbank.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.youbank.R
import com.example.youbank.databinding.FragmentHomeScreenBinding
import com.example.youbank.fragments.buttomModals.AccountSupportDialogFragment

class HomeScreenFragment: Fragment() {

    private var _binding: FragmentHomeScreenBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_screen_motion, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.profileImg.setOnClickListener {
            AccountSupportDialogFragment.newInstance().show(childFragmentManager, "dialog")
        }
    }
}