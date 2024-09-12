package com.example.praktikumlayout.activity

// SplashActivity.kt
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.praktikumlayout.R

class Splash : AppCompatActivity() {

    private val SPLASH_DELAY: Long = 2000 // 2 seconds delay

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler().postDelayed({
            // After delay, start the main activity
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
            finish() // Finish splash activity so the user can't go back to it
        }, SPLASH_DELAY)
    }
}
