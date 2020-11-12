package com.example.app01

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.app01.databinding.FragmentLoginMainBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class LoginMainFragment : Fragment() {
    val database: FirebaseDatabase = FirebaseDatabase.getInstance()
    var mRef = database.getReference().child("test")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding : FragmentLoginMainBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_login_main, container, false)
        mRef.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                System.out.println("X")
            }

            override fun onDataChange(p0: DataSnapshot) {
                for (a in p0.children) {
                    System.out.println("O")
                    System.out.println(a.value)
                }
            }

        })
        binding.buttonLogin.setOnClickListener {
            it.findNavController().navigate(R.id.action_loginMainFragment_to_workerScheduleFragment)
        }
        binding.button.setOnClickListener {
            System.out.println("work?")
            mRef.push().setValue("2")
        }

        return binding.root
    }
}