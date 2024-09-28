package com.st10079970.prixfinance

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView

class Dashboard : AppCompatActivity() {

    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var drwLayout: DrawerLayout
    private lateinit var navView: NavigationView
    private lateinit var recView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_dashboard)

        // Set up the toolbar
        val toolbar: Toolbar = findViewById(R.id.toolBarDashboard)
        setSupportActionBar(toolbar)

        // Set up DrawerLayout and ActionBarDrawerToggle
        setupDrawerLayout(toolbar)

        // Set up NavigationView
        navView = findViewById(R.id.navView_dashboard)
        setupNavigationView()

        // Set up Recycler Display
        recView = findViewById(R.id.recViewBudgetDisplay)
        recView.layoutManager = LinearLayoutManager(this)
        recView.setHasFixedSize(true)
    }

    //setting up the display and configuration of toolbar
    private fun setupDrawerLayout(toolbar: Toolbar) {
        drwLayout = findViewById(R.id.drwLayoutDashboard)
        toggle =  ActionBarDrawerToggle(this, drwLayout, toolbar, R.string.tgl_open, R.string.tgl_close)
        drwLayout.addDrawerListener(toggle)
        toggle.syncState()
    }

    //routing the navigation to the correct layout
    private fun setupNavigationView() {

        navView.setNavigationItemSelectedListener {
            drwLayout.closeDrawer(GravityCompat.END)
            when(it.itemId) {
                R.id.itmDashboard -> {
                    intent = Intent(this, Dashboard::class.java)
                    startActivity(intent)
                }
                R.id.itmNotifications -> {
                    intent = Intent(this, Notifications::class.java)
                    startActivity(intent)
                }
                R.id.itmBudgetManagement -> {
                    intent = Intent(this, Budgets::class.java)
                    startActivity(intent)
                }
                R.id.itmFinancialOverview -> {
                    intent = Intent(this, Overview::class.java)
                    startActivity(intent)
                }
                R.id.itmTransactions -> {
                    intent = Intent(this, Transactions::class.java)
                    startActivity(intent)
                }
                R.id.itmGoals -> {
                    intent = Intent(this, Goals::class.java)
                    startActivity(intent)
                }
                R.id.itmSettings -> {
                    intent = Intent(this, Settings::class.java)
                    startActivity(intent)
                }
                R.id.itmLogout -> {
                    //to configure logout
                }
            }
            true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (toggle.onOptionsItemSelected(item)) true else super.onOptionsItemSelected(item)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        toggle.onConfigurationChanged(newConfig)
    }

    //count ought to equal the number of budgets there are
    private fun setupBudgetsDisplay(){
        recView = findViewById(R.id.recViewBudgetDisplay)


        recView.removeAllViews()

        //hardcoded counter, should be automatic
        val itemCount = 3

        for (i in 0 until itemCount){
            OverlappingBudgetItemView(this).apply {
                setFloater("Food", "On Track", 120804.00)
                recView.addView(this)
            }
        }
    }
}