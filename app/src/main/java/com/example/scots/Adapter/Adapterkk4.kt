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
import com.example.firebase_martryatussofia_20.Activity.Showkk4
import com.example.firebase_martryatussofia_20.Mapel.Kk4
import com.example.firebase_martryatussofia_20.R
import com.google.firebase.database.FirebaseDatabase

@Suppress("UNREACHABLE_CODE", "NAME_SHADOWING")
class Adapterkk4 (val mCtx: Context, val layoutResId: Int, val list: List<Kk4> )
    : ArrayAdapter<Kk4>(mCtx,layoutResId,list) {

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

        val kk4 = list[position]

        textKelas.text = kk4.kelas
        textNoAbsen.text = kk4.noabsen
        textNama.text = kk4.nama
        textMateri.text = kk4.materi
        textNilai.text = kk4.nilai

        textUpdate.setOnClickListener {
            showUpdateDialog(kk4)
        }
        textDelete.setOnClickListener {
            Deleteinfo(kk4)
        }

        return view

    }

    private fun showUpdateDialog(kk4: Kk4) {
        val builder = AlertDialog.Builder(mCtx)

        builder.setTitle("Update")

        val inflater = LayoutInflater.from(mCtx)

        val view = inflater.inflate(R.layout.update, null)

        val textKelas = view.findViewById<EditText>(R.id.etxtKelas)
        val textNoAbsen = view.findViewById<EditText>(R.id.etxtAbsen)
        val textNama = view.findViewById<EditText>(R.id.etxtNama)
        val textMateri = view.findViewById<EditText>(R.id.etxtmateri)
        val textNilai = view.findViewById<EditText>(R.id.etxtNilai)

        textKelas.setText(kk4.kelas)
        textNoAbsen.setText(kk4.noabsen)
        textNama.setText(kk4.nama)
        textMateri.setText(kk4.materi)
        textNilai.setText(kk4.nilai)

        builder.setView(view)

        builder.setPositiveButton("Update") { dialog, which ->

            val dbKk4 = FirebaseDatabase.getInstance().getReference("KK4")

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

            val kk4 = Kk4(kk4.id, kelas, noabsen, nama, materi, nilai)

            dbKk4.child(kk4.id).setValue(kk4).addOnCompleteListener {
                Toast.makeText(mCtx, "Updated", Toast.LENGTH_SHORT).show()
            }

        }

        builder.setNegativeButton("No") { dialog, which ->

        }

        val alert = builder.create()
        alert.show()

    }


    private fun Deleteinfo(kk4: Kk4) {
        val progressDialog = ProgressDialog(
            context,
            com.google.android.material.R.style.Theme_MaterialComponents_Light_Dialog
        )
        progressDialog.isIndeterminate = true
        progressDialog.setMessage("Deleting...")
        progressDialog.show()
        val mydatabase = FirebaseDatabase.getInstance().getReference("KK4")
        mydatabase.child(kk4.id).removeValue()
        Toast.makeText(mCtx, "Deleted!!", Toast.LENGTH_SHORT).show()
        val intent = Intent(context, Showkk4::class.java)
        context.startActivity(intent)
    }
}

