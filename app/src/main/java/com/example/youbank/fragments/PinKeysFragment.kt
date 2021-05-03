package com.example.youbank.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.example.youbank.R

class PinKeysFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        view?.findViewById<Button>(R.id.btn0)?.setOnClickListener {
            Toast.makeText(this.context, "Button 0", Toast.LENGTH_SHORT).show()
        }

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pin_keys, container, false)
    }

}