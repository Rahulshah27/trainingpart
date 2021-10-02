package com.example.trainingpart

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
        R.id.practice_navigation -> {
            goToPracticeNavigation()
            true
        }

        R.id.bottom_navigation -> {
            goToBottomNavigation()
            true
        }
        else -> super.onOptionsItemSelected(item)
        }
    }

    private fun goToPracticeNavigation() {
        Toast.makeText(this, "please click on another option!", Toast.LENGTH_SHORT).show()

    }

    private fun goToBottomNavigation() {
        val intent = Intent(this, BottomNavigationActivity::class.java)
        startActivity(intent)
    }
}