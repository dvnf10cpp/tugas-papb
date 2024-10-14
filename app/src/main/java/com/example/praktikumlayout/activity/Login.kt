package com.example.praktikumlayout.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.praktikumlayout.R
import com.example.praktikumlayout.domain.account.Account
import com.example.praktikumlayout.domain.account.AccountService

class Login : AppCompatActivity() {
    private lateinit var registerRedirect: TextView
    private lateinit var loginButton: Button
    private lateinit var emailEt: EditText
    private lateinit var passwordEt: EditText
    private lateinit var loginWarningTv: TextView

    private val accountService = AccountService.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        setupViews()
        setupListener()
        setupInsetListener()
        setupPrefs()
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
        loginWarningTv = findViewById(R.id.login_warning)
    }

    private fun setupListener() {
        registerRedirect.setOnClickListener {
            navigateToRegister()
        }

        loginButton.setOnClickListener {
            attemptLogin()
        }
    }

    private fun setupPrefs() {
        val userPref = getSharedPreferences("UserSettings", MODE_PRIVATE)

        val isLoggedIn = userPref.getBoolean("isLoggedIn", false)

        if (isLoggedIn) {
            navigateToHome()
        }
    }

    private fun navigateToRegister() {
        val intent = Intent(this, Register::class.java)
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
        startActivity(intent)
        overridePendingTransition(0, 0)
    }

    private fun navigateToHome() {
        val intent = Intent(this, MrHead::class.java)
        startActivity(intent)
    }

    private fun attemptLogin() {
        val emailText = emailEt.text.toString()
        val passwordText = passwordEt.text.toString()
        val credential = Account(
            email = emailText,
            password = passwordText
        )

        // implement login
        val res = accountService.accountMatched(credential) == true
        if (!res) {
            loginWarningTv.text = "Invalid Email or Password"
            return
        }

        val user = accountService.getAccount(credential)

        val userPref = getSharedPreferences("UserSettings", MODE_PRIVATE)
        with(userPref.edit()) {
            putBoolean("isLoggedIn", true)
            putString("fullname", user.fullname)
            putString("email", user.email)

            apply()
        }

        navigateToHome()
    }
}