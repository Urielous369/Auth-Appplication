package com.example.authapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.authapplication.databinding.FragmentUserInterfaceBinding

class UserInterfaceFragment : Fragment() {

    lateinit var binding: FragmentUserInterfaceBinding
    private lateinit var viewModel: MainViewModel
    val arg : UserInterfaceFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentUserInterfaceBinding.inflate(layoutInflater)

        binding.nameOfCurrentUser.text = arg.myArg

        return binding.root
    }

    companion object {

        @JvmStatic
        fun newInstance() = UserInterfaceFragment()
    }
}