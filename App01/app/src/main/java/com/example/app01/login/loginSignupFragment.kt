package com.example.app01.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import com.example.app01.DB.retrofitAPI
import com.example.app01.MainActivity
import com.example.app01.R
import com.example.app01.dataObject
import com.example.app01.databinding.FragmentLoginSignupBinding
import com.example.app01.dto.User
import retrofit2.Retrofit

class loginSignupFragment : Fragment() {

    private lateinit var binding : FragmentLoginSignupBinding
    private var roleChecked : Boolean = false
    private lateinit var newUser: User

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
                if (createNewUser(binding.newUser!!)) {
                    (activity as MainActivity).alertToast("Successfully signed up! Please log in.")
                    signupSuccess()
                } else {
                    (activity as MainActivity).alertToast("Existed account name. Please change account name.")
                }
            } else {
                (activity as MainActivity).alertToast("Please check again if you fill every condition.")
            }
        }

        return binding.root
    }

    // Return true if success, false if fail
    fun createNewUser(newUser : User) : Boolean {
        var result = false
        var checkVar = User()

        // Check duplication
        var searchUserByAccount = Thread(Runnable {
            checkVar = (activity as MainActivity).getRetrofitAPI().searchUserByAccount(newUser.account).execute().body()!!
        })
        searchUserByAccount.start()
        searchUserByAccount.join()

        if (checkVar.account == "allowed") {
            var thread = Thread(Runnable {
                result = (activity as MainActivity).getRetrofitAPI().createAccount(newUser).execute().body()!!
            })
            thread.start()
            thread.join()
        } else {
            result = false
        }
        return result
    }

    fun signupSuccess() {
        NavHostFragment.findNavController(this)
            .navigate(R.id.action_loginSignupFragment_to_loginMainFragment)
    }

}