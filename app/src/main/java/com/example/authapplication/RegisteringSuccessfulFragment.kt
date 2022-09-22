package com.example.authapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.authapplication.databinding.FragmentRegisteringSuccessfulBinding

class RegisteringSuccessfulFragment : Fragment() {

    private lateinit var binding: FragmentRegisteringSuccessfulBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRegisteringSuccessfulBinding.inflate(layoutInflater)

        binding.successfulBtn.setOnClickListener {
            val moveToLoginFragment = RegisteringSuccessfulFragmentDirections.actionRegisteringSuccessfulFragmentToLoginFragment()
            findNavController().navigate(moveToLoginFragment)
        }

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = RegisteringSuccessfulFragment()
    }
}