package com.example.youbank.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.youbank.R
import com.google.android.material.button.MaterialButton
import com.google.android.material.checkbox.MaterialCheckBox


class CardOverviewFragment : Fragment() {

    private lateinit var localController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_card_overview, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnShowInformation: MaterialButton = view.findViewById(R.id.btnShowInformation)
        val btnShowPIN: MaterialButton = view.findViewById(R.id.btnShowPIN)

/*
        val cardIcon: ImageView = view.findViewById(R.id.cardIcon)
        val cardLogo: ImageView = view.findViewById(R.id.cardLogo)
        val omniTxt: TextView = view.findViewById(R.id.omniTxt)
        val activeOrFrozenTxt: TextView = view.findViewById(R.id.activeOrFrozenTxt)
        val cardNumberLabel: TextView = view.findViewById(R.id.cardNumberLabel)
        val cardNumber: TextView = view.findViewById(R.id.cardNumberValue)*/


        // Below is shown when "show information" button is clicked
        /*val infoCardLogo: ImageView = view.findViewById(R.id.infoCardLogo)
        val infoOmniTxt: TextView = view.findViewById(R.id.infoOmniTxt)
        val infoActiveOrFrozenTxt: TextView = view.findViewById(R.id.infoActiveOrFrozenTxt)

        val infoCardNumberLabel: TextView = view.findViewById(R.id.infoCardNumberLabel)
        val infoCardNumber: TextView = view.findViewById(R.id.infoCardNumber)
        val infoCCVLabel: TextView = view.findViewById(R.id.infoCCVLabel)
        val infoCCV: TextView = view.findViewById(R.id.infoCCV)
        val infoExpiryDateLabel: TextView = view.findViewById(R.id.infoExpiryDateLabel)
        val infoExpiryDate: TextView = view.findViewById(R.id.infoExpiryDate)*/


        /*freezeCheckbox.setOnCheckedChangeListener { buttonView, isChecked ->
            if(freezeCheckbox.isChecked)
            {
                activeOrFrozenTxt.setTextColor(ContextCompat.getColor(view.context,
                    R.color.redColor
                ))
                activeOrFrozenTxt.text = "Frozen"
            }
            else
            {
                activeOrFrozenTxt.setTextColor(ContextCompat.getColor(view.context,
                    R.color.brand_main
                ))
                activeOrFrozenTxt.text = "Active"
            }
        }*/

        /*btnShowInformation.setOnClickListener {
            // Hiding views/Showing when clicked again
            *//*showHide(cardIcon)
            showHide(cardLogo)
            showHide(omniTxt)
            showHide(activeOrFrozenTxt)
            showHide(cardNumberLabel)
            showHide(cardNumber)*//*

            // Showing information views/Hiding when clicked again
            *//*showHide(infoCardLogo)
            showHide(infoOmniTxt)
            showHide(infoActiveOrFrozenTxt)
            showHide(infoCardNumberLabel)
            showHide(infoCardNumber)
            showHide(infoCCVLabel)
            showHide(infoCCV)
            showHide(infoExpiryDateLabel)
            showHide(infoExpiryDate)*//*
        }

        btnShowPIN.setOnClickListener {

        }*/

        /*btnShowInformation.setOnClickListener(this)
        btnShowPIN.setOnClickListener(this)*/


        btnShowInformation.setOnClickListener {
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

        btnShowPIN.setOnClickListener {
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


    /*private fun showHide(view:View) {
        view.visibility = if (view.visibility == View.VISIBLE){
            View.GONE
        } else{
            View.VISIBLE


        }
    }*/


    /*override fun onClick(v: View?) {
        if(v?.id == R.id.btnShowInformation){

            childFragmentManager.findFragmentById(R.id.nav_host_card_fragment) as NavHostFragment
            findNavController().navigate(R.id.action_cardVerticalFragment_to_cardVerticalInformationFragment)

        }
        else if (v?.id == R.id.btnShowPIN){



        }
    }*/

}

