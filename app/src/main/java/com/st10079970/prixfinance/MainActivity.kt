package com.st10079970.prixfinance

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Set padding for system bars
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Find the button and set the click listener to redirect to LoginActivity
        val btnGetStarted = findViewById<Button>(R.id.btnGetStarted)  // Adjust to the correct button ID
        btnGetStarted.setOnClickListener {
            // Launch the LoginActivity when the button is clicked
            val intent = Intent(this, Dashboard::class.java)
            startActivity(intent)
        }
    }
}