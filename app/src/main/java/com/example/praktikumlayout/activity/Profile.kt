package com.example.praktikumlayout.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.praktikumlayout.R
import com.example.praktikumlayout.domain.account.Account
import com.example.praktikumlayout.domain.account.AccountService

class Profile : AppCompatActivity() {
    private lateinit var logoutBtn: ImageView
    private lateinit var homeBtn: ImageView
    private lateinit var updateBtn: Button
    private lateinit var fullnameEt: EditText
    private lateinit var emailEt: EditText
    private lateinit var passwordEt: EditText

    private val accountSvc = AccountService.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_profile)

        setupViews()
        setupListener()
        setupPrefs()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun setupPrefs() {
        val userPref = getSharedPreferences("UserSettings", MODE_PRIVATE)

        val fullname = userPref.getString("fullname", "")
        val email = userPref.getString("email", "")

        fullnameEt.setText(fullname)
        emailEt.setText(email)
    }

    private fun updatePrefs() {
        val fullname = fullnameEt.text.toString()
        val email = emailEt.text.toString()

        val userPref = getSharedPreferences("UserSettings", MODE_PRIVATE)

        with(userPref.edit()) {
            putString("fullname", fullname)
            putString("email", email)

            apply()
        }
    }

    private fun setupViews() {
        logoutBtn = findViewById(R.id.logout_btn)
        homeBtn = findViewById(R.id.home_btn)
        updateBtn = findViewById(R.id.update_btn)
        fullnameEt = findViewById(R.id.fullname_input)
        emailEt = findViewById(R.id.email_input)
        passwordEt = findViewById(R.id.password_input)
    }

    private fun setupListener() {
        logoutBtn.setOnClickListener {
            logout()
        }

        homeBtn.setOnClickListener {
            navigateToHome()
        }

        updateBtn.setOnClickListener {
            update()
        }
    }

    private fun update() {

        val email = emailEt.text.toString()
        val password = passwordEt.text.toString()

        val account = Account(
            email,
            password
        )

        accountSvc.updateAccount(account)
        updatePrefs()
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
        val intent = Intent(this, Login::class.java)

        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)

        startActivity(intent)
    }

    private fun navigateToHome() {
        val intent = Intent(this, Home::class.java)

        startActivity(intent)
        overridePendingTransition(0, 0)
    }
}