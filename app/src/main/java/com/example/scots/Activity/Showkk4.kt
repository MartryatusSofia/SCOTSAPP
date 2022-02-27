package com.example.firebase_martryatussofia_20.Activity

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.firebase_martryatussofia_20.Adapter.Adapterkk4
import com.example.firebase_martryatussofia_20.Mapel.Kk4
import com.example.firebase_martryatussofia_20.R
import com.google.firebase.database.*


class Showkk4 : AppCompatActivity() {
    lateinit var ref : DatabaseReference
    lateinit var list : MutableList<Kk4>
    lateinit var listView: ListView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_showkk4)
        ref = FirebaseDatabase.getInstance().getReference("KK4")
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
                        val kk4 = h.getValue(Kk4::class.java)
                        list.add(kk4!!)
                    }
                    val adapter = Adapterkk4(this@Showkk4,R.layout.kk4,list)
                    listView.adapter = adapter
                }
            }
        })
    }
}
