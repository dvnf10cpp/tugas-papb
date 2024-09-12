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
import com.example.praktikumlayout.domain.account.Account
import com.example.praktikumlayout.domain.account.AccountService

class RegisterGraphicsGuruji : AppCompatActivity() {
    private lateinit var loginRedirect: TextView
    private lateinit var registerBtn: Button
    private lateinit var fullnameEt: EditText
    private lateinit var emailEt: EditText
    private lateinit var passwordEt: EditText
    private lateinit var registerWarning: TextView

    private val accountService = AccountService.getInstance()

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
        fullnameEt = findViewById(R.id.fullname_input)
        emailEt = findViewById(R.id.email_input)
        passwordEt = findViewById(R.id.password_input)
        registerWarning = findViewById(R.id.register_warning)
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
        val fullname = fullnameEt.text.toString()
        val email = emailEt.text.toString()
        val password = passwordEt.text.toString()

        val account = Account(
            fullname,
            email,
            password
        )

        if (fullname.isEmpty() || email.isEmpty() || password.isEmpty()) {
            // invalid validation
            registerWarning.text = "Input cannot be empty"
            return
        }

        if (accountService.isAlreadyRegistered(account)) {
            // already registered
            registerWarning.text = "Email already registered"
            return
        }

        accountService.registerAccount(account)

        navigateToLogin()
    }
}