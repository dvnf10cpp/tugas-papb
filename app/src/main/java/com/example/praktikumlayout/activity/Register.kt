package com.example.praktikumlayout.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.praktikumlayout.R
import com.example.praktikumlayout.domain.account.Account
import com.example.praktikumlayout.domain.account.AccountService
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

class Register : AppCompatActivity() {
    private lateinit var loginRedirect: TextView
    private lateinit var registerBtn: Button
    private lateinit var emailEt: EditText
    private lateinit var passwordEt: EditText
    private lateinit var registerWarning: TextView

    private val accountService = AccountService.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)

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
        val intent = Intent(this, Login::class.java)
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
        startActivity(intent)
        overridePendingTransition(0, 0)
    }

    private fun attemptRegister() {

        val email = emailEt.text.toString()
        val password = passwordEt.text.toString()

        val account = Account(
            email,
            password
        )

        lifecycleScope.launch {
            val resp = accountService.registerAccount(account)

            if (resp.code != 200) {
                Toast.makeText(this@Register, resp.message, Toast.LENGTH_SHORT).show()

            } else {
                val intent = Intent(this@Register, Login::class.java)
                startActivity(intent)
            }
        }

    }
}