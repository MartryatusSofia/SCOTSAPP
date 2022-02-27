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
import com.example.firebase_martryatussofia_20.Activity.Showmatematika
import com.example.firebase_martryatussofia_20.Mapel.Matematika
import com.example.firebase_martryatussofia_20.R
import com.google.firebase.database.FirebaseDatabase

@Suppress("UNREACHABLE_CODE", "NAME_SHADOWING")
class Adaptermatematika(val mCtx: Context, val layoutResId: Int, val list: MutableList<Matematika>)
    : ArrayAdapter<Matematika>(mCtx,layoutResId,list) {

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

        val matematika = list[position]

        textKelas.text = matematika.kelas
        textNoAbsen.text = matematika.noabsen
        textNama.text = matematika.nama
        textMateri.text = matematika.materi
        textNilai.text = matematika.nilai

        textUpdate.setOnClickListener {
            showUpdateDialog(matematika)
        }
        textDelete.setOnClickListener {
            Deleteinfo(matematika)
        }

        return view

    }

    private fun showUpdateDialog(matematika: Matematika) {
        val builder = AlertDialog.Builder(mCtx)

        builder.setTitle("Update")

        val inflater = LayoutInflater.from(mCtx)

        val view = inflater.inflate(R.layout.updatematematika, null)

        val textKelas = view.findViewById<EditText>(R.id.etxtKelas)
        val textNoAbsen = view.findViewById<EditText>(R.id.etxtAbsen)
        val textNama = view.findViewById<EditText>(R.id.etxtNama)
        val textMateri = view.findViewById<EditText>(R.id.etxtmateri)
        val textNilai = view.findViewById<EditText>(R.id.etxtNilai)

        textKelas.setText(matematika.kelas)
        textNoAbsen.setText(matematika.noabsen)
        textNama.setText(matematika.nama)
        textMateri.setText(matematika.materi)
        textNilai.setText(matematika.nilai)

        builder.setView(view)

        builder.setPositiveButton("Update") { dialog, which ->

            val dbMatematika = FirebaseDatabase.getInstance().getReference("MATEMATIKA")

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

            val matematika = Matematika(matematika.id, kelas, noabsen, nama, materi, nilai)

            dbMatematika.child(matematika.id).setValue(matematika).addOnCompleteListener {
                Toast.makeText(mCtx, "Updated", Toast.LENGTH_SHORT).show()
            }

        }

        builder.setNegativeButton("No") { dialog, which ->

        }

        val alert = builder.create()
        alert.show()

    }


    private fun Deleteinfo(matematika: Matematika){
        val progressDialog = ProgressDialog(
            context,
            com.google.android.material.R.style.Theme_MaterialComponents_Light_Dialog
        )
        progressDialog.isIndeterminate = true
        progressDialog.setMessage("Deleting...")
        progressDialog.show()
        val mydatabase = FirebaseDatabase.getInstance().getReference("MATEMATIKA")
        mydatabase.child(matematika.id).removeValue()
        Toast.makeText(mCtx, "Deleted!!", Toast.LENGTH_SHORT).show()
        val intent = Intent(context, Showmatematika::class.java)
        context.startActivity(intent)
    }
}
