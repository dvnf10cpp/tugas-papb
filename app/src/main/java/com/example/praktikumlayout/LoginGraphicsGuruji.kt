package com.example.praktikumlayout

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LoginGraphicsGuruji : AppCompatActivity() {
    private lateinit var registerRedirect: TextView
    private lateinit var loginButton: Button
    private lateinit var emailEt: EditText
    private lateinit var passwordEt: EditText
    private val viewModel: State get() = SharedStateView.viewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login_graphics_guruji)

        setupViews()
        setupListener()
        setupInsetListener()
    }

    private fun setupInsetListener() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun setupViews() {
        registerRedirect = findViewById(R.id.register_redirect)
        loginButton = findViewById(R.id.login_btn)
        emailEt = findViewById(R.id.email_et)
        passwordEt = findViewById(R.id.password_et)
    }

    private fun setupListener() {
        registerRedirect.setOnClickListener {
            navigateToRegister()
        }

        loginButton.setOnClickListener {
            attemptLogin()
        }
    }

    private fun navigateToRegister() {
        val intent = Intent(this, RegisterGraphicsGuruji::class.java)
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
        startActivity(intent)
    }

    private fun attemptLogin() {
        val emailText = emailEt.text.toString()
        val passwordText = passwordEt.text.toString()

        // implement login

        val intent = Intent(this, HomeGraphicsGuruji::class.java)
        startActivity(intent)
    }
}