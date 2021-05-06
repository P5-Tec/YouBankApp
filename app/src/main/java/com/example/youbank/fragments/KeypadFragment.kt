package com.example.youbank.fragments

import android.media.Image
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.youbank.R

class KeypadFragment : Fragment(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_keypad, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //val circle2: ImageView = view.findViewById(R.id.circl2)
        //val circle3: ImageView = view.findViewById(R.id.circl3)
        //val circle4: ImageView = view.findViewById(R.id.circl4)

        val btn1: Button = view.findViewById(R.id.btn1)
        val btn2: Button = view.findViewById(R.id.btn2)
        val btn3: Button = view.findViewById(R.id.btn3)
        val btn4: Button = view.findViewById(R.id.btn4)
        val btn5: Button = view.findViewById(R.id.btn5)
        val btn6: Button = view.findViewById(R.id.btn6)
        val btn7: Button = view.findViewById(R.id.btn7)
        val btn8: Button = view.findViewById(R.id.btn8)
        val btn9: Button = view.findViewById(R.id.btn9)
        val btn0: Button = view.findViewById(R.id.btn0)

        btn1.setOnClickListener(this)
        btn2.setOnClickListener(this)
        btn3.setOnClickListener(this)
        btn4.setOnClickListener(this)
        btn5.setOnClickListener(this)
        btn6.setOnClickListener(this)
        btn7.setOnClickListener(this)
        btn8.setOnClickListener(this)
        btn9.setOnClickListener(this)
        btn0.setOnClickListener(this)
    }

    private val correctString: String = "5555"
    private var valueString: String = ""

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn1 -> {
                password(1)
            }
            R.id.btn2 -> {
                password(2)
            }
            R.id.btn3 -> {
                password(3)
            }
            R.id.btn4 -> {
                password(4)
            }
            R.id.btn5 -> {
                password(5)
            }
            R.id.btn6 -> {
                password(6)
            }
            R.id.btn7 -> {
                password(7)
            }
            R.id.btn8 -> {
                password(8)
            }
            R.id.btn9 -> {
                password(9)
            }
            R.id.btn0 -> {
                password(0)
            }
        }
    }

    private fun password(value: Int) {
        valueString += value;
        //Toast.makeText(this.context, valueString, Toast.LENGTH_SHORT).show()

        highlightCircles()

        if (valueString.length == 4) {
            if (valueString == correctString) {
                Toast.makeText(this.context, "Correct", Toast.LENGTH_SHORT).show()
                valueString = ""
                findNavController().navigate(R.id.action_keypadFragment_to_cardHorizontalFragment)
            } else {
                Toast.makeText(this.context, "Incorrect", Toast.LENGTH_SHORT).show()
                valueString = ""
            }
        }

        highlightCircles()
    }

    private fun highlightCircles() {

        val circle1: ImageView? = view?.findViewById(R.id.circl1)
        val circle2: ImageView? = view?.findViewById(R.id.circl2)
        val circle3: ImageView? = view?.findViewById(R.id.circl3)
        val circle4: ImageView? = view?.findViewById(R.id.circl4)

        when (valueString.length) {
            1 -> {
                circle1?.setImageResource(R.drawable.circle_white)
            }
            2 -> {
                circle2?.setImageResource(R.drawable.circle_white)
            }
            3 -> {
                circle3?.setImageResource(R.drawable.circle_white)
            }
            4 -> {
                circle4?.setImageResource(R.drawable.circle_white)
            }
            else -> {
                circle1?.setImageResource(R.drawable.ic_baseline_circle_24)
                circle2?.setImageResource(R.drawable.ic_baseline_circle_24)
                circle3?.setImageResource(R.drawable.ic_baseline_circle_24)
                circle4?.setImageResource(R.drawable.ic_baseline_circle_24)
            }
        }
    }
}

