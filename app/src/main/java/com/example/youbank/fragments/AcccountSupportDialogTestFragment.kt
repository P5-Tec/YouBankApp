package com.example.youbank.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.youbank.R
import com.example.youbank.fragments.AccountSupportDialogFragment


class AcccountSupportDialogTestFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_freeze_card_test, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.freeze).setOnClickListener {
            //ItemListDialogFragment.newInstance(30).show(childFragmentManager, "dialog",)
            AccountSupportDialogFragment.newInstance().show(childFragmentManager, "dialog")

        }
    }

}