package com.example.app01

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.app01.databinding.FragmentLoginMainBinding

class LoginMainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding : FragmentLoginMainBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_login_main, container, false)
        binding.buttonLogin.setOnClickListener {
            it.findNavController().navigate(R.id.action_loginMainFragment_to_workerScheduleFragment)
        }

        return binding.root
    }
}