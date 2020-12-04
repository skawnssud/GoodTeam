package com.example.app01.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.example.app01.DB.retrofitAPI
import com.example.app01.MainActivity
import com.example.app01.R
import com.example.app01.dataObject
import com.example.app01.databinding.FragmentLoginMainBinding
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class LoginMainFragment : Fragment() {

    private lateinit var binding: FragmentLoginMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_login_main, container, false
        )
        binding.buttonLogin.setOnClickListener {
            // Check id~pw validation
            var check: Boolean = false
            if (binding.id != null || binding.pw != null
                || binding.id == "" || binding.pw == ""
                || binding.id!!.contains("\\") || binding.pw!!.contains("\\")) {
                var thread = Thread(Runnable {
                    check =
                        (activity as MainActivity).getRetrofitAPI().checkValidation(binding.id.toString(), binding.pw.toString())
                            .execute().body()!!
                })
                thread.start()
                thread.join()
                if (check) {
                    var thread2 = Thread(Runnable {
                        dataObject.selectUser = (activity as MainActivity).getRetrofitAPI().getUserInfo(binding.id.toString()).execute().body()!!
                    })
                    thread2.start()
                    thread2.join()
                    loginSuccess()
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Invalid login. Try again.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                Toast.makeText(
                    requireContext(),
                    "Invalid login. Try again.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        binding.buttonSignup.setOnClickListener {
            moveToSignup()
        }

        return binding.root
    }

    fun moveToSignup() {
        NavHostFragment.findNavController(this)
            .navigate(R.id.action_loginMainFragment_to_loginSignupFragment)
    }

    fun loginSuccess() {
        // Send user to appropriate fragment: 0 -> Boss | 1 -> Worker
        if (dataObject.selectUser.role == 0) {
            (activity as MainActivity).getBranchesByIdBoss(dataObject.selectUser.id)
            NavHostFragment.findNavController(this)
                .navigate(R.id.action_loginMainFragment_to_historyFragment)
        } else {
            NavHostFragment.findNavController(this)
                .navigate(R.id.action_loginMainFragment_to_workerScheduleFragment)
        }
    }
}