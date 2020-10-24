package com.example.app01

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.login_main.view.*

class loginMain : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.login_main, container, false)
        view.buttonLogin.setOnClickListener {
            (activity as MainActivity).changeFragWorker(10)
        }
        return view
    }
}