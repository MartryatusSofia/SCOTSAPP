package com.example.firebase_martryatussofia_20

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val inputUsername: EditText = findViewById(R.id.username)
        val inputPassword: EditText = findViewById(R.id.password)
        val btnlogin: Button = findViewById(R.id.btnlogin)
        val username = "Admin"
        val password = "******"


        btnlogin.setOnClickListener {
            if (inputUsername.length() == 0) {
                val toast = Toast.makeText(this,"Harap masukkan username", Toast.LENGTH_SHORT)
                toast.show()
                if (inputPassword.length() == 0) {
                    val toast2 = Toast.makeText(this,"Harap masukkan password", Toast.LENGTH_SHORT)
                    toast2.show()}


            }else{

                val inputusername = inputUsername.getText().toString()
                if(inputusername==username){
                    val toast = Toast.makeText(this,"Admin", Toast.LENGTH_SHORT)
                    toast.show()
                    val intent = Intent(this, DashboardActivity::class.java)
                    startActivity(intent)




                }else{
                    val toast = Toast.makeText(this, "Username dan Password tidak terdaftar", Toast.LENGTH_SHORT)
                    toast.show()

                }

            }


            if (inputUsername.length() == 0) {
                val toast = Toast.makeText(this,"Harap masukkan password", Toast.LENGTH_SHORT)
                toast.show()

            }else{
                //toString untuk convert ke tipe data string
                val inputPassword = inputPassword.getText().toString()
                if(inputPassword==password){
                    val intent = Intent(this, DashboardActivity::class.java)
                    startActivity(intent)


                }else{

                }
            }
        }
    }

}
