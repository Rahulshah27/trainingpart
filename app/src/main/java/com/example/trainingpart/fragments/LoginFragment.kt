package com.example.trainingpart.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.trainingpart.AuthNavigationActivity
import com.example.trainingpart.Constants
import com.example.trainingpart.PracticeNavigationActivity
import com.example.trainingpart.R
import kotlinx.android.synthetic.main.fragment_login.view.*


class LoginFragment : Fragment(R.layout.fragment_login) {

    @SuppressLint("RestrictedApi")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val etUserName = view.et_l_username
        val etPassword = view.et_l_password
        val btnLogin = view.loginBtn
        val tvLogin = view.tv_sign_up

        btnLogin.setOnClickListener{

            if (etUserName.text.toString().trim().isEmpty()){
                etUserName.error = "User name cannot be empty!"
                return@setOnClickListener
            }
            if (etPassword.text.toString().trim().isEmpty()){
                etPassword.error = "Password cannot be empty!"
                return@setOnClickListener
            }

            val sharedPreferences: SharedPreferences? =
                context?.getSharedPreferences(Constants.SharedPref.myData, Context.MODE_PRIVATE)
            when {
                sharedPreferences?.getString("UserName", "").isNullOrEmpty() -> {
                    Toast.makeText(context, "Username not found", Toast.LENGTH_SHORT).show()
                }
                sharedPreferences?.getString("Password", "").isNullOrEmpty() -> {
                    Toast.makeText(context, "Password is incorrect", Toast.LENGTH_SHORT).show()
                }

                else -> {
                    Toast.makeText(this.context,"You have success fully Logged in", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this.context, PracticeNavigationActivity::class.java)
                    startActivity(intent)

                    (requireContext() as AuthNavigationActivity).finish()
                }
            }
        }
        tvLogin.setOnClickListener {
            val navController: NavController = Navigation.findNavController(view)
            navController.navigate(R.id.action_loginFragment_to_signUpFragment)
            navController.backStack
        }
    }
}