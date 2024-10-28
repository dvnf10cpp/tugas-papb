package com.example.praktikumlayout.activity

import android.R.attr
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.praktikumlayout.R


class Camera : AppCompatActivity() {
    private lateinit var homeBtn: ImageView
    private lateinit var changeBtn: Button
    private lateinit var imageView: ImageView
    private lateinit var contactBtn: Button
    val resultContract = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val bitmap = result?.data?.extras?.get("data") as Bitmap
            Log.d("PHOTO_DEBUG", "bitmap: $bitmap")
            imageView.setImageBitmap(bitmap)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_camera)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setupViews()
        setupListeners()
    }

    fun setupViews() {
        homeBtn = findViewById(R.id.home_btn)
        changeBtn = findViewById(R.id.ganti_foto)
        contactBtn = findViewById(R.id.contact_us)
        imageView = findViewById(R.id.imageView)
    }

    fun setupListeners() {
        homeBtn.setOnClickListener {
            val intent = Intent(this, MrHead::class.java)

            startActivity(intent)
        }

        changeBtn.setOnClickListener {

            openCamera()
        }

        contactBtn.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.setData(Uri.parse("https://api.whatsapp.com/send?phone=6287822036868"))
            startActivity(intent)
        }
    }

    fun openCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        resultContract.launch(intent)
    }
}