package com.example.firebase_martryatussofia_20.Insert

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import com.example.firebase_martryatussofia_20.Activity.Showkk4
import com.example.firebase_martryatussofia_20.Activity.Showmatematika
import com.example.firebase_martryatussofia_20.DashboardActivity
import com.example.firebase_martryatussofia_20.Mapel.Kk4
import com.example.firebase_martryatussofia_20.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_insertkk4.*

class Insertmatematika : AppCompatActivity() {
    lateinit var ref: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insertmatematika)

        ref = FirebaseDatabase.getInstance().getReference("MATEMATIKA")

        val imageView : ImageView = findViewById(R.id.imgout)
        imageView.setOnClickListener { viewDetail()}

        val imageView1 : ImageView = findViewById(R.id.imgsave)
        imageView1.setOnClickListener { viewlist()}

        btnSave.setOnClickListener {
            savedata()

            val intent = Intent (this, Showmatematika::class.java)
            startActivity(intent)


        }
    }

    private fun viewlist() {
        val intent = Intent (this, Showkk4::class.java)
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

        val kk4Id = ref.push().key.toString()
        val kk4 = Kk4(kk4Id, kelas, noabsen, nama, materi, nilai)

        ref.child(kk4Id).setValue(kk4).addOnCompleteListener {
            Toast.makeText(this, "Successs", Toast.LENGTH_SHORT).show()
            etxtKelas.setText("")
            etxtAbsen.setText("")
            etxtNama.setText("")
            etxtmateri.setText("")
            etxtNilai.setText("")

        }
    }
}