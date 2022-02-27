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
import com.example.firebase_martryatussofia_20.Activity.Showpjok
import com.example.firebase_martryatussofia_20.Mapel.Pjok
import com.example.firebase_martryatussofia_20.R
import com.google.firebase.database.FirebaseDatabase

@Suppress("UNREACHABLE_CODE", "NAME_SHADOWING")
class Adapterpjok (val mCtx: Context, val layoutResId: Int, val list: List<Pjok> )
    : ArrayAdapter<Pjok>(mCtx,layoutResId,list) {

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

        val pjok = list[position]

        textKelas.text = pjok.kelas
        textNoAbsen.text = pjok.noabsen
        textNama.text = pjok.nama
        textMateri.text = pjok.materi
        textNilai.text = pjok.nilai

        textUpdate.setOnClickListener {
            showUpdateDialog(pjok)
        }
        textDelete.setOnClickListener {
            Deleteinfo(pjok)
        }

        return view

    }

    private fun showUpdateDialog(pjok: Pjok) {
        val builder = AlertDialog.Builder(mCtx)

        builder.setTitle("Update")

        val inflater = LayoutInflater.from(mCtx)

        val view = inflater.inflate(R.layout.update, null)

        val textKelas = view.findViewById<EditText>(R.id.etxtKelas)
        val textNoAbsen = view.findViewById<EditText>(R.id.etxtAbsen)
        val textNama = view.findViewById<EditText>(R.id.etxtNama)
        val textMateri = view.findViewById<EditText>(R.id.etxtmateri)
        val textNilai = view.findViewById<EditText>(R.id.etxtNilai)

        textKelas.setText(pjok.kelas)
        textNoAbsen.setText(pjok.noabsen)
        textNama.setText(pjok.nama)
        textMateri.setText(pjok.materi)
        textNilai.setText(pjok.nilai)

        builder.setView(view)

        builder.setPositiveButton("Update") { dialog, which ->

            val dbPjok = FirebaseDatabase.getInstance().getReference("PJOK")

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

            val pjok = Pjok(pjok.id, kelas, noabsen, nama, materi, nilai)

            dbPjok.child(pjok.id).setValue(pjok).addOnCompleteListener {
                Toast.makeText(mCtx, "Updated", Toast.LENGTH_SHORT).show()
            }

        }

        builder.setNegativeButton("No") { dialog, which ->

        }

        val alert = builder.create()
        alert.show()

    }


    private fun Deleteinfo(pjok: Pjok) {
        val progressDialog = ProgressDialog(
            context,
            com.google.android.material.R.style.Theme_MaterialComponents_Light_Dialog
        )
        progressDialog.isIndeterminate = true
        progressDialog.setMessage("Deleting...")
        progressDialog.show()
        val mydatabase = FirebaseDatabase.getInstance().getReference("PJOK")
        mydatabase.child(pjok.id).removeValue()
        Toast.makeText(mCtx, "Deleted!!", Toast.LENGTH_SHORT).show()
        val intent = Intent(context, Showpjok::class.java)
        context.startActivity(intent)
    }
}

