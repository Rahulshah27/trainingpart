package com.example.trainingpart

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class BottomNavigationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_navigation)

        val btmNavView = findViewById<BottomNavigationView>(R.id.bottomNavView)
        val navHost = supportFragmentManager.findFragmentById(R.id.navHostBottomNav) as NavHostFragment

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_search, R.id.navigation_orders, R.id.navigation_profile
            )
        )
        setupActionBarWithNavController(navHost.findNavController(), appBarConfiguration)
        btmNavView.setupWithNavController(navHost.findNavController())
    }
}