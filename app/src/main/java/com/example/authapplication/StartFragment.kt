package com.example.authapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.authapplication.databinding.FragmentStartBinding


class StartFragment : Fragment() {

    private lateinit var binding: FragmentStartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentStartBinding.inflate(layoutInflater)

        binding.btnLogin.setOnClickListener {

            val moveToLoginFragment = StartFragmentDirections.actionStartFragmentToLoginFragment()

            findNavController().navigate(moveToLoginFragment)
        }

        binding.btnRegister.setOnClickListener {
            val moveToRegisterFragment = StartFragmentDirections.actionStartFragmentToRegisterFragment()

            findNavController().navigate(moveToRegisterFragment)
        }

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = StartFragment()
    }
}