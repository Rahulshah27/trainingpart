package com.example.trainingpart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController

class PracticeNavigationActivity : AppCompatActivity() {
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_practice_navigation)

        val navHost = supportFragmentManager.findFragmentById(R.id.container) as NavHostFragment
        navHost.findNavController().setGraph(R.navigation.nav_practice_activity)
        navController = navHost.navController
    }
}