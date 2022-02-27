package com.example.firebase_martryatussofia_20.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.example.firebase_martryatussofia_20.Adapter.Adapterkk4
import com.example.firebase_martryatussofia_20.Adapter.Adapterpjok
import com.example.firebase_martryatussofia_20.Mapel.Kk4
import com.example.firebase_martryatussofia_20.Mapel.Pjok
import com.example.firebase_martryatussofia_20.R
import com.google.firebase.database.*

class Showpjok : AppCompatActivity() {
    lateinit var ref : DatabaseReference
    lateinit var list : MutableList<Pjok>
    lateinit var listView: ListView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_showpjok)
        ref = FirebaseDatabase.getInstance().getReference("PJOK")
        list = mutableListOf()
        listView = findViewById(R.id.listView)

        ref.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot) {
                if (p0!!.exists()){

                    list.clear()
                    for (h in p0.children){
                        val pjok = h.getValue(Pjok::class.java)
                        list.add(pjok!!)
                    }
                    val adapter = Adapterpjok(this@Showpjok,R.layout.pjok,list)
                    listView.adapter = adapter
                }
            }
        })
    }
}
