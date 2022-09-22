package com.example.authapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.authapplication.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentRegisterBinding.inflate(layoutInflater)

        binding.btnRegister.setOnClickListener {

            // traitement de l'email
            if (!viewModel.registerEmailErrorValue() && viewModel.registerEmailEdit.value!!.isNotEmpty()) {

                // traitement du username
                if (!viewModel.usernameVerify() && viewModel.registerUsernameEdit.value!!.isNotEmpty()){
                    RegisterFragment

                    // traitement du nom complet
                    if (!viewModel.fullNameVerify() && viewModel.registerFullName.value!!.isNotEmpty()){

                        val moveToRegisteringSuccessfulFragment = RegisterFragmentDirections.actionRegisterFragmentToRegisteringSuccessfulFragment()
                        findNavController().navigate(moveToRegisteringSuccessfulFragment)

                    } else {
                        Toast.makeText(requireContext(), "Veuillez renseigner le nom complet", Toast.LENGTH_SHORT).show()
                    } // traitement du nom complet
                } else {
                    Toast.makeText(requireContext(), "Le nom d'utilisateur est obligatoire", Toast.LENGTH_SHORT).show()
                } // traitement du username
            } else {
                Toast.makeText(requireContext(), "Saisissez ou vérifiez l'email !", Toast.LENGTH_SHORT).show()
            } // traitement de l'email

        }

        binding.fullnameEdit.doOnTextChanged { text, start, before, count ->
            viewModel.registerFullName.value = text.toString()
        }

        binding.registerEmailEdit.doOnTextChanged { text, start, before, count ->
            viewModel.registerEmailEdit.value = text.toString()
            viewModel.validEmail()
        }

        binding.registerUsernameEdit.doOnTextChanged { text, start, before, count ->
            viewModel.registerUsernameEdit.value = text.toString()
        }

        binding.registerPasswordEdit.doOnTextChanged { text, start, before, count ->
            viewModel.registerPasswordEdit.value = text.toString()
        }

        viewModel.registerEmailError.observe(requireActivity()) { error ->
            if (error) {
                binding.registerEmailErrorShow.text = "Entrez une adresse Email valid"
            } else {
                binding.registerEmailErrorShow.text = ""
            }
        }

        viewModel.registerPasswordEdit.observe(requireActivity()) {
            if (it.isEmpty()) {
                binding.btnRegister.isFocusable = false
                binding.btnRegister.isClickable = false
                binding.btnRegister.isEnabled = false
            } else {
                binding.btnRegister.isFocusable = true
                binding.btnRegister.isClickable = true
                binding.btnRegister.isEnabled = true
            }
        }

        binding.haveAccount.setOnClickListener {
            val moveToLogin = RegisterFragmentDirections.actionRegisterFragmentToLoginFragment()
            findNavController().navigate(moveToLogin)
        }

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = RegisterFragment()
    }
}