package com.example.praktikumlayout

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class HomeGraphicsGuruji : AppCompatActivity() {
    private lateinit var logoutBtn: ImageView
    private lateinit var welcomeTv: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home_graphics_guruji)

        setupViews()
        setupListener()
        setupPrefs()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun setupViews() {
        logoutBtn = findViewById(R.id.logout_btn)
        welcomeTv = findViewById(R.id.welcome)
    }

    private fun setupPrefs() {
        val userPref = getSharedPreferences("UserSettings", MODE_PRIVATE)

        val fullname = userPref.getString("fullname", "")

        welcomeTv.text = "Welcome $fullname"
    }

    private fun setupListener() {
        logoutBtn.setOnClickListener {
            logout()
        }
    }

    private fun logout() {
        val userPref = getSharedPreferences("UserSettings", MODE_PRIVATE)

        with(userPref.edit()) {
            remove("fullname")
            remove("isLoggedIn")

            apply()
        }

        navigateToLogin()
    }

    private fun navigateToLogin() {
        val intent = Intent(this, LoginGraphicsGuruji::class.java)
        startActivity(intent)
    }
}