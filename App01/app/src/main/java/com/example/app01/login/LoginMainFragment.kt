package com.example.app01.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import com.example.app01.R
import com.example.app01.databinding.FragmentLoginMainBinding

class LoginMainFragment : Fragment() {

    private lateinit var binding : FragmentLoginMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_login_main, container, false)
        binding.buttonLogin.setOnClickListener {
            if (binding.id.equals("1") && binding.pw.equals("1")) {
                loginSuccess()
            }
            else {
                Toast.makeText(context, "Invalid login. Try again.", Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root
    }

    fun loginSuccess() {
        NavHostFragment.findNavController(this).navigate(R.id.action_loginMainFragment_to_historyFragment)
    }
}