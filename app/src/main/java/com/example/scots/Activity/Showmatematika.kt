package com.example.firebase_martryatussofia_20.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.example.firebase_martryatussofia_20.Adapter.Adaptermatematika
import com.example.firebase_martryatussofia_20.Mapel.Matematika
import com.example.firebase_martryatussofia_20.R
import com.google.firebase.database.*

class Showmatematika : AppCompatActivity() {
    lateinit var ref : DatabaseReference
    lateinit var list : MutableList<Matematika>
    lateinit var listView: ListView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_showmatematika)
        ref = FirebaseDatabase.getInstance().getReference("MATEMATIKA")
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
                        val matematika = h.getValue(Matematika::class.java)
                        list.add(matematika!!)
                    }
                    val adapter = Adaptermatematika(this@Showmatematika,R.layout.matematika,list)
                    listView.adapter = adapter
                }
            }
        })
    }
}
