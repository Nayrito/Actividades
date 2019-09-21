package com.example.loginactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    var useremail = ""
    var userpass = ""



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        var user = FirebaseAuth.getInstance().currentUser
        tvhome.text = user?.displayName //displayEmail

        var datos_recibidos = intent.extras
         useremail = datos_recibidos?.getString("useremail").toString()
         userpass = datos_recibidos?.getString("userpass").toString()
        tvhome.text = useremail
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_overflow,menu)
        return true
    }



    override fun onBackPressed() {
        super.onBackPressed()
        var intent = Intent(Intent.ACTION_MAIN)
        intent.addCategory(Intent.CATEGORY_HOME)
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }
    fun logOutMenuOnClicked(item:MenuItem){
        FirebaseAuth.getInstance().signOut()
        goToLoginActivity()
    }

    private fun goToLoginActivity() {
        val intent = Intent(this,LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

}
