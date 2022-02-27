package com.example.firebase_martryatussofia_20.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.example.firebase_martryatussofia_20.Adapter.Adapterkk2
import com.example.firebase_martryatussofia_20.Mapel.Kk2
import com.example.firebase_martryatussofia_20.R
import com.google.firebase.database.*

class Showkk2 : AppCompatActivity() {
    lateinit var ref : DatabaseReference
    lateinit var list : MutableList<Kk2>
    lateinit var listView: ListView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_showkk2)
        ref = FirebaseDatabase.getInstance().getReference("KK2")
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
                        val kk2 = h.getValue(Kk2::class.java)
                        list.add(kk2!!)
                    }
                    val adapter = Adapterkk2(this@Showkk2,R.layout.kk2,list)
                    listView.adapter = adapter
                }
            }
        })
    }
}
