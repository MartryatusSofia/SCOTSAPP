package com.example.firebase_martryatussofia_20.Insert

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import com.example.firebase_martryatussofia_20.Activity.Showpjok
import com.example.firebase_martryatussofia_20.DashboardActivity
import com.example.firebase_martryatussofia_20.Mapel.Pjok
import com.example.firebase_martryatussofia_20.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_insert_pjok.*

class InsertPjok : AppCompatActivity() {
    lateinit var ref: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert_pjok)
        ref = FirebaseDatabase.getInstance().getReference("PJOK")

        val imageView : ImageView = findViewById(R.id.imgout)
        imageView.setOnClickListener { viewDetail()}

        val imageView1 : ImageView = findViewById(R.id.imgsave)
        imageView1.setOnClickListener { viewlist()}

        btnSave.setOnClickListener {
            savedata()

            val intent = Intent (this, Showpjok::class.java)
            startActivity(intent)


        }
    }

    private fun viewlist() {
        val intent = Intent (this, Showpjok::class.java)
        startActivity(intent)
    }

    private fun viewDetail() {
        val intent = Intent(this, DashboardActivity::class.java)
        startActivity(intent)
    }

    private fun savedata() {
        val kelas = etxtKelas.text.toString()
        val noabsen = etxtAbsen.text.toString()
        val nama = etxtNama.text.toString()
        val materi = etxtmateri.text.toString()
        val nilai = etxtNilai.text.toString()

        val pjokId = ref.push().key.toString()
        val pjok = Pjok(pjokId, kelas, noabsen, nama, materi, nilai)

        ref.child(pjokId).setValue(pjok).addOnCompleteListener {
            Toast.makeText(this, "Successs", Toast.LENGTH_SHORT).show()
            etxtKelas.setText("")
            etxtAbsen.setText("")
            etxtNama.setText("")
            etxtmateri.setText("")
            etxtNilai.setText("")

        }
    }
}