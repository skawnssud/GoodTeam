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
import com.example.app01.databinding.FragmentLoginMainBinding
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class LoginMainFragment : Fragment() {

    private lateinit var binding: FragmentLoginMainBinding
    private lateinit var mRetrofit: Retrofit
    private lateinit var mRetrofitAPI: retrofitAPI

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initServer()
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_login_main, container, false
        )
        binding.buttonLogin.setOnClickListener {
            // Check id~pw validation
            var check: Boolean = false
            if (binding.id != null && binding.pw != null) {
                var thread = Thread(Runnable {
                    check =
                        mRetrofitAPI.checkValidation(binding.id.toString(), binding.pw.toString())
                            .execute().body()!!
                })
                thread.start()
                thread.join()
                if (check) {
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

        return binding.root
    }

    fun loginSuccess() {
        NavHostFragment.findNavController(this)
            .navigate(R.id.action_loginMainFragment_to_historyFragment)
    }

    // Server Default Setting
    fun initServer() {
        mRetrofit = Retrofit.Builder().baseUrl("http://222.106.121.250:8080")
            .addConverterFactory(GsonConverterFactory.create()).build()
        mRetrofitAPI = mRetrofit.create(retrofitAPI::class.java)
    }
}