package com.example.youbank.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.youbank.R
import com.example.youbank.databinding.FragmentCardOverviewBinding

class CardOverviewFragment: Fragment() {

    private var _binding: FragmentCardOverviewBinding? = null
    private val binding get() = _binding!!
    private lateinit var localController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_card_overview, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.backbtn.setOnClickListener {
            findNavController().navigate(R.id.action_cardOverviewFragmentBackBtn)
        }

        binding.btnShowInformation.setOnClickListener {
            val localNavHostFragment =
                childFragmentManager.findFragmentById(R.id.nav_host_card_fragment) as NavHostFragment
            localController = localNavHostFragment.navController

            when (localController.currentDestination?.id) {
                R.id.cardVerticalFragment -> {
                    localController.navigate(R.id.action_cardVerticalFragment_to_cardVerticalInformationFragment)
                }
                R.id.cardVerticalPinFragment -> {
                    localController.navigate(R.id.action_cardVerticalPinFragment_to_cardVerticalInformationFragment)
                }
                else -> {
                    localController.navigate(R.id.action_cardVerticalInformationFragment_to_cardVerticalFragment)
                }
            }
        }

        binding.btnShowPIN.setOnClickListener {
            val localNavHostFragment =
                childFragmentManager.findFragmentById(R.id.nav_host_card_fragment) as NavHostFragment
            localController = localNavHostFragment.navController

            when (localController.currentDestination?.id) {
                R.id.cardVerticalFragment -> {
                    localController.navigate(R.id.action_cardVerticalFragment_to_cardVerticalPinFragment)
                }
                R.id.cardVerticalInformationFragment -> {
                    localController.navigate(R.id.action_cardVerticalInformationFragment_to_cardVerticalPinFragment)
                }
                else -> {
                    localController.navigate(R.id.action_cardVerticalPinFragment_to_cardVerticalFragment)
                }
            }
        }
    }
}

