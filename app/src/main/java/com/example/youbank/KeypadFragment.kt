package com.example.youbank

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast

class KeypadFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_keypad, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*view.findViewById<Button>(R.id.btn1).setOnClickListener {
            Toast.makeText(view.context, "Button 1 clicked", Toast.LENGTH_LONG).show()
            //findNavController().navigate(R.id.action_pinKeysFragment_to_FirstFragment)
        }*/

        val btn1: Button = view.findViewById(R.id.btn1)

        btn1.setOnClickListener {
            Toast.makeText(this.context, "Hejsa", Toast.LENGTH_LONG).show()
        }





        //view.findViewById<Button>(R.id.btn1).setOnClickListener { Toast.makeText(this.context, "Hejsa", Toast.LENGTH_LONG).show() }
    }


}

