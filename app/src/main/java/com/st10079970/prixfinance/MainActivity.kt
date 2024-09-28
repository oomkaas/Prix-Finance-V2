package com.st10079970.prixfinance

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.st10079970.prixfinance.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    var binding: ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.getRoot())
        replaceFragment(HomeFragment())
        binding!!.bottomNavigationView.setBackground(null)
        binding!!.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.getItemId()) {
                R.id.home -> replaceFragment(HomeFragment())
                R.id.budgetManagement -> replaceFragment(BudgetManagementFragment())
                R.id.notifications -> replaceFragment(NotificationsFragment())
                R.id.transactions -> replaceFragment(TransactionsFragment())
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
    }
}