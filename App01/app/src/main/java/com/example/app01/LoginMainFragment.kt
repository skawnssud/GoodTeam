package com.example.app01

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.app01.databinding.FragmentLoginMainBinding

class LoginMainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding : FragmentLoginMainBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_login_main, container, false)
        val id = binding.editId.toString()
        val pw = binding.editPw.toString()
        binding.buttonLogin.setOnClickListener {
            if (id.equals("1") && pw.equals("1")) {
                it.findNavController().navigate(R.id.action_loginMainFragment_to_historyFragment)
            }
            else {
                Toast.makeText(requireContext(), "로그인 정보가 일치하지 않습니다.", Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root
    }
}