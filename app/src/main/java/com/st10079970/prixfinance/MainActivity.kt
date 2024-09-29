package com.st10079970.prixfinance

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.navigation.NavigationView
import com.st10079970.prixfinance.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var drawerLayout: DrawerLayout
    var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.getRoot())

        // Drawer setup
        drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, R.string.open_nav, R.string.close_nav
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        // Bottom navigation setup
        binding!!.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> replaceFragment(HomeFragment())
                R.id.budgetManagement -> replaceFragment(BudgetManagementFragment())
                R.id.notifications -> replaceFragment(NotificationsFragment())
                R.id.transactions -> replaceFragment(TransactionsFragment())
            }
            true
        }

        // Default fragment
        if (savedInstanceState == null) {
            replaceFragment(HomeFragment())
            navigationView.setCheckedItem(R.id.nav_Home)
        }
    }

    // Handle Navigation Drawer item clicks
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_Home -> replaceFragment(HomeFragment())
            R.id.nav_BudgetManagement -> replaceFragment(BudgetManagementFragment())
            R.id.nav_Notifications -> replaceFragment(NotificationsFragment())
            R.id.nav_Transactions -> replaceFragment(TransactionsFragment())
            R.id.nav_Goals -> replaceFragment(GoalsFragment())
            R.id.nav_Settings -> replaceFragment(SettingsFragment())
            R.id.nav_Profile -> replaceFragment(ProfileFragment())
            R.id.nav_Logout -> Toast.makeText(this, "Logout!", Toast.LENGTH_SHORT).show()
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    // Method to replace fragments
    private fun replaceFragment(fragment: Fragment) {
        //val fragmentManager: FragmentManager = supportFragmentManager
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        //val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.commit()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            onBackPressedDispatcher.onBackPressed()
        }
    }
}