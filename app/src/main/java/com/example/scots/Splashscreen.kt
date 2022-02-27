package com.example.firebase_martryatussofia_20

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Splashscreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)
        val intentButton : Button = findViewById(R.id.btnmulai)
        intentButton.setOnClickListener { viewDetail()}
    }
    private fun viewDetail() {
        val intent =Intent(this, Login::class.java)
        startActivity(intent)
    }
}