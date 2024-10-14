package com.example.praktikumlayout.activity

import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.praktikumlayout.R

class MrHead : AppCompatActivity() {
    private lateinit var hairCheckBox: CheckBox
    private lateinit var moustacheCheckBox: CheckBox
    private lateinit var eyebrowCheckBox: CheckBox
    private lateinit var beardCheckBox: CheckBox
    private lateinit var hairImage: ImageView
    private lateinit var moustacheImage: ImageView
    private lateinit var eyebrowImage: ImageView
    private lateinit var beardImage: ImageView
    private lateinit var textWelcome: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_mr_head)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupViews()
        setupListeners()
        setupPrefs()
    }

    private fun setupPrefs() {
        val userPref = getSharedPreferences("UserSettings", MODE_PRIVATE)

        val email = userPref.getString("email", "")

        textWelcome.setText("Welcome $email")
    }

    private fun setupViews() {
        hairCheckBox = findViewById(R.id.checkRambut)
        moustacheCheckBox = findViewById(R.id.checkKumis)
        eyebrowCheckBox = findViewById(R.id.checkAlis)
        beardCheckBox = findViewById(R.id.checkJanggut)

        hairImage = findViewById(R.id.hairImage)
        moustacheImage = findViewById(R.id.moustacheImage)
        eyebrowImage = findViewById(R.id.eyebrowImage)
        beardImage = findViewById(R.id.beardImage)

        textWelcome = findViewById(R.id.textWelcome)
    }

    private fun setupListeners() {
        hairCheckBox.setOnCheckedChangeListener { _, isChecked ->
            toggleCheck(isChecked, hairImage)
        }
        moustacheCheckBox.setOnCheckedChangeListener { _, isChecked ->
            toggleCheck(isChecked, moustacheImage)
        }
        eyebrowCheckBox.setOnCheckedChangeListener { _, isChecked ->
            toggleCheck(isChecked, eyebrowImage)
        }
        beardCheckBox.setOnCheckedChangeListener { _, isChecked ->
            toggleCheck(isChecked, beardImage)
        }

    }

    private fun toggleCheck(isChecked: Boolean, image: ImageView) {
        if (isChecked) {
            image.visibility = View.GONE
        } else {
            image.visibility = View.VISIBLE
        }
    }
}