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
import com.example.authapplication.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel : MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentLoginBinding.inflate(layoutInflater)

        binding.btnLogin.setOnClickListener {

          if (!viewModel.emailErrorValue() && viewModel.emailEdit.value!!.isNotEmpty()) {
              // A faire !
             if (viewModel.login()){
                 val action = LoginFragmentDirections.actionLoginFragmentToUserInterfaceFragment(viewModel.emailEdit.value!!,viewModel.emailEdit.value!!)
                 findNavController().navigate(action)
             } else {
                 Toast.makeText(requireContext(), "Email ou mot de passe incorrect !", Toast.LENGTH_SHORT).show()
             }
          } else {
              Toast.makeText(requireContext(), "Saisissez ou vérifiez l'email !", Toast.LENGTH_SHORT).show()
          }
        }

       /* if (viewModel.passwordEdit.value == null) {
            binding.btnLogin.isEnabled = false
        } else {
            binding.btnLogin.isEnabled = true
        }*/

        binding.loginEmailEdit.doOnTextChanged { text, start, before, count ->
            viewModel.emailEdit.value = text.toString()
            viewModel.validEmail()
        }

        binding.loginPasswordEdit.doOnTextChanged { text, start, before, count ->
            viewModel.passwordEdit.value = text.toString()
        }

        viewModel.emailError.observe(requireActivity()) { error ->
            if (error) {
                binding.loginEmailErrorShow.text = "Email invalid"
            } else {
                binding.loginEmailErrorShow.text = ""
            }
        }

        viewModel.passwordEdit.observe(requireActivity()) {
            if (it.isEmpty()) {
                binding.btnLogin.isFocusable = false
                binding.btnLogin.isClickable = false
                binding.btnLogin.isEnabled = false
            } else {
                binding.btnLogin.isFocusable = true
                binding.btnLogin.isClickable = true
               binding.btnLogin.isEnabled = true
            }
        }

        binding.haventAccount.setOnClickListener {
            val moveToRegister = LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
            findNavController().navigate(moveToRegister)
        }

        return binding.root
    }


    companion object {
        @JvmStatic
        fun newInstance() = LoginFragment()
    }
}