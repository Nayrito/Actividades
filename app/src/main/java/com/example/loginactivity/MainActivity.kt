package com.example.loginactivity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat.startActivityForResult
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onButtonClicked(view:View) {
        if (view is Button){
            when (view.id) {
                R.id.btLogin -> {
                    var email = etEmail.text.toString()
                    var pass = etPassword.text.toString()
                    if (email.isEmpty()) {
                        Toast.makeText(this, "ingrese el correo", Toast.LENGTH_SHORT).show()
                    } else if (pass.isEmpty()) {
                        Toast.makeText(this, "ingrese la contraseña", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "usuario no registrado", Toast.LENGTH_SHORT).show()
                    }
                }
                R.id.btSign_in -> {
                    etEmail.text.clear()
                    etPassword.text.clear()
                    var intent = Intent(this, RegistroActivity::class.java)
                    startActivityForResult(intent, 123)
                }

            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == 123 && resultCode == Activity.RESULT_OK) {

                var email_recive = data!!.extras?.getString("useremail")
                var pass_recive = data.extras?.getString("userpass")
                btLogin.setOnClickListener {
                    var email = etEmail.text.toString()
                    var pass = etPassword.text.toString()
                    if (email.isEmpty()) {
                        Toast.makeText(this, "ingrese el correo", Toast.LENGTH_SHORT).show()
                    } else if (pass.isEmpty()) {
                        Toast.makeText(this, "ingrese la contraseña", Toast.LENGTH_SHORT).show()
                    } else if (!(email.equals(email_recive)) || !(pass.equals(pass_recive))) {
                        Toast.makeText(this, "Error en usuario o contraseña", Toast.LENGTH_SHORT).show()
                    } else {
                        etEmail.text.clear()
                        etPassword.text.clear()
                        var intent1 = Intent(this, HomeActivity::class.java)
                        intent1.putExtra("useremail", email)
                        intent1.putExtra("userpass", pass)
                        startActivityForResult(intent1, 123)

                    }
                }
        }
    }
}
