package com.example.praktikumlayout

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class RegisterGraphicsGuruji : AppCompatActivity() {
    private lateinit var loginRedirect: TextView
    private lateinit var registerBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register_graphics_guruji)

        setupViews()
        setupListener()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun setupViews() {
        loginRedirect = findViewById(R.id.login_redirect)
        registerBtn = findViewById(R.id.register_btn)
    }

    private fun setupListener() {
        loginRedirect.setOnClickListener {
            navigateToLogin()
        }

        registerBtn.setOnClickListener {
            attemptRegister()
        }
    }

    private fun navigateToLogin() {
        val intent = Intent(this, LoginGraphicsGuruji::class.java)
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
        startActivity(intent)
    }

    private fun attemptRegister() {
        // TODO: register with db
    }
}