package com.st10079970.prixfinance

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class splashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash_screen)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Find the button and set the click listener to redirect to LoginActivity
        val btn4Test2 = findViewById<Button>(R.id.btnGetStarted2)  // Adjust to the correct button ID
        btn4Test2.setOnClickListener {
            // Launch the LoginActivity when the button is clicked
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}