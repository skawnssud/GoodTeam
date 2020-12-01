package com.example.app01.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.app01.DB.retrofitAPI
import com.example.app01.MainActivity
import com.example.app01.R
import com.example.app01.databinding.FragmentLoginSignupBinding
import com.example.app01.dto.User
import retrofit2.Retrofit

class loginSignupFragment : Fragment() {

    private lateinit var binding : FragmentLoginSignupBinding
    private var roleChecked : Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_login_signup, container, false)
        binding.newUser = User()
        binding.buttonBoss.setOnClickListener {
            binding.newUser!!.role = 0
            roleChecked = true
        }
        binding.buttonWorker.setOnClickListener {
            binding.newUser!!.role = 1
            roleChecked = true
        }
        binding.buttonSignup.setOnClickListener {
            if (binding.newUser!!.account != "null" && binding.newUser!!.nick != null && binding.newUser!!.pw != "null" && roleChecked) {
                createNewUser()
            } else {
                (activity as MainActivity).alertToast("Please check again if you fill every condition.")
            }
        }

        return binding.root
    }

    fun createNewUser() : Boolean {
        var result = false
        var thread = Thread(Runnable {
            result = (activity as MainActivity).getRetrofitAPI().createAccount(binding.newUser!!).execute().body()!!
        })
        thread.start()
        thread.join()
        return result
    }

}