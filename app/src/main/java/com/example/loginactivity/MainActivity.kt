package com.example.loginactivity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.core.content.ContextCompat.startActivity
import com.example.loginactivity.ui.main.SectionsPagerAdapter
import com.facebook.login.LoginManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity : AppCompatActivity() {
    private var user = FirebaseAuth.getInstance().currentUser
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)
        if(user != null) {
            saveUser(user)
        }



    }
    override fun onBackPressed() {
        super.onBackPressed()
        var intent = Intent(Intent.ACTION_MAIN)
        intent.addCategory(Intent.CATEGORY_HOME)
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_overflow,menu)
        return true
    }

    fun logOutMenuOnClicked(item:MenuItem){

        FirebaseAuth.getInstance().signOut()
        LoginManager.getInstance().logOut()
        goToLoginActivity()
    }
    fun PerfilMenuOnClicked(item:MenuItem){
        val intent = Intent(this,Perfil_Activity::class.java)
        startActivity(intent)

    }

    private fun goToLoginActivity() {
        val intent = Intent(this,LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
    private fun saveUser(user: FirebaseUser?) {

        val name = user?.displayName
        val id = user?.uid.toString()
        val email = user?.email


        val user = User(name,email,id )
        val database= FirebaseDatabase.getInstance()
        val myRef = database.getReference("usuarios")
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                for (snapshot: DataSnapshot in dataSnapshot.children){
                    if(!snapshot.child(id).exists()){
                        myRef.child(id).setValue(user)
                    }


                }
                //userAdapter.notifyDataSetChanged()

            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w("value", "Failed to read value.", error.toException())
            }
        })


    }
}