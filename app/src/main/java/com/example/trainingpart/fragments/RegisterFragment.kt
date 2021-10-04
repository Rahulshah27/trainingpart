package com.example.trainingpart.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.trainingpart.Constants
import com.example.trainingpart.R
import kotlinx.android.synthetic.main.fragment_register.view.*

class RegisterFragment :  Fragment(R.layout.fragment_register) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val etUserName = view.et_r_username
        val etPassword = view.et_r_pass
        val etRePassword = view.et_r_confirmpass

        val sharedPreferences: SharedPreferences? = context?.getSharedPreferences(Constants.SharedPref.myData, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor? = sharedPreferences?.edit()

        view.registerBtn.setOnClickListener {
            if (etUserName.text.toString().trim().isEmpty()){
                etUserName.error = "User name is required!"
                return@setOnClickListener
            }
            if (etPassword.text.toString().trim().isEmpty()){
                etPassword.error = "Password is required!"
                return@setOnClickListener

            }
            if (etRePassword.text.toString().trim().isEmpty()){
                etRePassword.error = "Please confirm the password"
                return@setOnClickListener
            }
            else
            {
                editor?.putString("UserName", etUserName.text.toString())?.apply()
                editor?.putString("Password", etPassword.text.toString())?.apply()
                Toast.makeText(this.context,
                        "${etUserName.text} is added successfully",
                        Toast.LENGTH_SHORT).show()
                    val navController: NavController = Navigation.findNavController(view)
                    navController.navigate(R.id.action_registerFragment_to_loginFragment)
                    navController.popBackStack()
            }
         }

        view.tv_login.setOnClickListener {
            val navController: NavController = Navigation.findNavController(view)
            navController.navigate(R.id.action_registerFragment_to_loginFragment)
            navController.popBackStack()
        }
        }

    }

