package com.example.firebase_martryatussofia_20

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.firebase_martryatussofia_20.Insert.InsertPjok
import com.example.firebase_martryatussofia_20.Insert.Insertkk2
import com.example.firebase_martryatussofia_20.Insert.Insertkk4
import com.example.firebase_martryatussofia_20.Insert.Insertmatematika

class DashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val imageView : ImageView = findViewById(R.id.btnout)
        imageView.setOnClickListener { viewDetail()}
        val imageView1 : ImageView = findViewById(R.id.imgkk4)
        imageView1.setOnClickListener { viewkk4()}
        val imageView2 : ImageView = findViewById(R.id.imgkk2)
        imageView2.setOnClickListener { viewkk2()}
        val imageView3 : ImageView = findViewById(R.id.imgmatematika)
        imageView3.setOnClickListener { viewmatematika()}
        val imageView4 : ImageView = findViewById(R.id.imgpjok)
        imageView4.setOnClickListener { viewpjok()}
    }
    private fun viewDetail() {
        val intent = Intent(this, Login::class.java)
        startActivity(intent)

    }
    private fun viewkk4() {
        val intent = Intent(this, Insertkk4::class.java)
        startActivity(intent)

    }
    private fun viewkk2() {
        val intent = Intent(this, Insertkk2::class.java)
        startActivity(intent)

    }
    private fun viewmatematika() {
        val intent = Intent(this, Insertmatematika::class.java)
        startActivity(intent)

    }
    private fun viewpjok() {
        val intent = Intent(this, InsertPjok::class.java)
        startActivity(intent)

    }
}