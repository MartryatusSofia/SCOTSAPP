package com.example.firebase_martryatussofia_20.Adapter

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.firebase_martryatussofia_20.Activity.Showkk2
import com.example.firebase_martryatussofia_20.Mapel.Kk2
import com.example.firebase_martryatussofia_20.R
import com.google.firebase.database.FirebaseDatabase

@Suppress("UNREACHABLE_CODE", "NAME_SHADOWING")
class Adapterkk2 (val mCtx: Context, val layoutResId: Int, val list: List<Kk2> )
    : ArrayAdapter<Kk2>(mCtx,layoutResId,list) {

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater: LayoutInflater = LayoutInflater.from(mCtx)
        val view: View = layoutInflater.inflate(layoutResId, null)

        val textKelas = view.findViewById<TextView>(R.id.textKelas)
        val textNoAbsen = view.findViewById<TextView>(R.id.textNoAbsen)
        val textNama = view.findViewById<TextView>(R.id.textNama)
        val textMateri = view.findViewById<TextView>(R.id.textMateri)
        val textNilai = view.findViewById<TextView>(R.id.textNilai)
        val textUpdate = view.findViewById<TextView>(R.id.textUpdate)
        val textDelete = view.findViewById<TextView>(R.id.textDelete)

        val kk2 = list[position]

        textKelas.text = kk2.kelas
        textNoAbsen.text = kk2.noabsen
        textNama.text = kk2.nama
        textMateri.text = kk2.materi
        textNilai.text = kk2.nilai

        textUpdate.setOnClickListener {
            showUpdateDialog(kk2)
        }
        textDelete.setOnClickListener {
            Deleteinfo(kk2)
        }

        return view

    }

    private fun showUpdateDialog(kk2: Kk2) {
        val builder = AlertDialog.Builder(mCtx)

        builder.setTitle("Update")

        val inflater = LayoutInflater.from(mCtx)

        val view = inflater.inflate(R.layout.update, null)

        val textKelas = view.findViewById<EditText>(R.id.etxtKelas)
        val textNoAbsen = view.findViewById<EditText>(R.id.etxtAbsen)
        val textNama = view.findViewById<EditText>(R.id.etxtNama)
        val textMateri = view.findViewById<EditText>(R.id.etxtmateri)
        val textNilai = view.findViewById<EditText>(R.id.etxtNilai)

        textKelas.setText(kk2.kelas)
        textNoAbsen.setText(kk2.noabsen)
        textNama.setText(kk2.nama)
        textMateri.setText(kk2.materi)
        textNilai.setText(kk2.nilai)

        builder.setView(view)

        builder.setPositiveButton("Update") { dialog, which ->

            val dbKk2 = FirebaseDatabase.getInstance().getReference("KK2")

            val kelas = textKelas.text.toString().trim()

            val noabsen = textNoAbsen.text.toString().trim()

            val nama = textNama.text.toString().trim()

            val materi = textMateri.text.toString().trim()

            val nilai = textNilai.text.toString().trim()

            if (kelas.isEmpty()) {
                textKelas.error = "please enter kelas"
                textKelas.requestFocus()
                return@setPositiveButton
            }

            if (noabsen.isEmpty()) {
                textNoAbsen.error = "please enter no absen"
                textNoAbsen.requestFocus()
                return@setPositiveButton
            }

            if (nama.isEmpty()) {
                textNama.error = "please enter nama"
                textNama.requestFocus()
                return@setPositiveButton
            }

            if (materi.isEmpty()) {
                textMateri.error = "please enter materi"
                textMateri.requestFocus()
                return@setPositiveButton
            }

            if (nilai.isEmpty()) {
                textNilai.error = "please enter nilai"
                textNilai.requestFocus()
                return@setPositiveButton
            }

            val kk2 = Kk2(kk2.id, kelas, noabsen, nama, materi, nilai)

            dbKk2.child(kk2.id).setValue(kk2).addOnCompleteListener {
                Toast.makeText(mCtx, "Updated", Toast.LENGTH_SHORT).show()
            }

        }

        builder.setNegativeButton("No") { dialog, which ->

        }

        val alert = builder.create()
        alert.show()

    }


    private fun Deleteinfo(kk2: Kk2) {
        val progressDialog = ProgressDialog(
            context,
            com.google.android.material.R.style.Theme_MaterialComponents_Light_Dialog
        )
        progressDialog.isIndeterminate = true
        progressDialog.setMessage("Deleting...")
        progressDialog.show()
        val mydatabase = FirebaseDatabase.getInstance().getReference("KK2")
        mydatabase.child(kk2.id).removeValue()
        Toast.makeText(mCtx, "Deleted!!", Toast.LENGTH_SHORT).show()
        val intent = Intent(context, Showkk2::class.java)
        context.startActivity(intent)
    }
}

