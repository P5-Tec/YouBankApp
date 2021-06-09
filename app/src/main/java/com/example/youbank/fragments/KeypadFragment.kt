package com.example.youbank.fragments

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.youbank.R
import com.example.youbank.databinding.FragmentKeypadBinding
import com.example.youbank.viewModels.KeypadViewModel

class KeypadFragment: Fragment(), View.OnClickListener {

    private lateinit var viewModel: KeypadViewModel
    private var _binding: FragmentKeypadBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentKeypadBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(KeypadViewModel::class.java)
        viewModel.getName().observe(viewLifecycleOwner, { x -> binding.txtNameOfUser.text = x.toString() })

        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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

        binding.btnForgotPassword.setOnClickListener {
            findNavController().navigate(R.id.action_keypadFragment_to_forgotPasswordFragment)
        }
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
        valueString += value

        highlightCircles()

        if (valueString.length == 4) {
            if (valueString == correctString) {
                Toast.makeText(this.context, "Correct", Toast.LENGTH_SHORT).show()
                valueString = ""
                findNavController().navigate(R.id.action_keypadFragment_to_homeScreenFragment)
            }
            else {
                Toast.makeText(this.context, "Incorrect", Toast.LENGTH_SHORT).show()
                valueString = ""
            }
        }

        highlightCircles()
    }

    private fun highlightCircles() {

        val circle1: ImageView = binding.circl1
        val circle2: ImageView = binding.circl2
        val circle3: ImageView = binding.circl3
        val circle4: ImageView = binding.circl4

        when (context?.resources?.configuration?.uiMode?.and(Configuration.UI_MODE_NIGHT_MASK)) {

            Configuration.UI_MODE_NIGHT_YES -> {
                when (valueString.length) {
                    1 -> {
                        circle1.setColorFilter(
                            ContextCompat.getColor(requireContext(), R.color.drac_orange))
                    }
                    2 -> {
                        circle2.setColorFilter(
                            ContextCompat.getColor(requireContext(), R.color.drac_orange))
                    }
                    3 -> {
                        circle3.setColorFilter(
                            ContextCompat.getColor(requireContext(), R.color.drac_orange))
                    }
                    4 -> {
                        circle4.setColorFilter(
                            ContextCompat.getColor(requireContext(), R.color.drac_orange))
                    }
                    else -> {
                        circle1.setColorFilter(
                            ContextCompat.getColor(requireContext(), R.color.white)
                        )
                        circle2.setColorFilter(
                            ContextCompat.getColor(requireContext(), R.color.white)
                        )
                        circle3.setColorFilter(
                            ContextCompat.getColor(requireContext(), R.color.white)
                        )
                        circle4.setColorFilter(
                            ContextCompat.getColor(requireContext(), R.color.white)
                        )
                    }
                }
            }
            Configuration.UI_MODE_NIGHT_NO -> {
                when (valueString.length) {
                    1 -> {
                        circle1.setColorFilter(
                            ContextCompat.getColor(requireContext(), R.color.n_l_main))
                    }
                    2 -> {
                        circle2.setColorFilter(
                            ContextCompat.getColor(requireContext(), R.color.n_l_main))
                    }
                    3 -> {
                        circle3.setColorFilter(
                            ContextCompat.getColor(requireContext(), R.color.n_l_main))
                    }
                    4 -> {
                        circle4.setColorFilter(
                            ContextCompat.getColor(requireContext(), R.color.n_l_main))
                    }
                    else -> {
                        circle1.setColorFilter(
                            ContextCompat.getColor(requireContext(), R.color.black)
                        )
                        circle2.setColorFilter(
                            ContextCompat.getColor(requireContext(), R.color.black)
                        )
                        circle3.setColorFilter(
                            ContextCompat.getColor(requireContext(), R.color.black)
                        )
                        circle4.setColorFilter(
                            ContextCompat.getColor(requireContext(), R.color.black)
                        )
                    }
                }
            }
        }
    }
}