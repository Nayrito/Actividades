package com.example.loginactivity


import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.fragment_spots.view.*


class SpotsFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private var spotlist: ArrayList<Spot> = ArrayList()
    private lateinit var storage: FirebaseStorage

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //var s1= Spot("Niquia","av35b","street","publico")
       // var s2= Spot("Madera","av36b","Vert","publico")
        //spotlist.add(s1)
        //spotlist.add(s2)

        storage = FirebaseStorage.getInstance()


        val root =  inflater.inflate(R.layout.fragment_spots, container, false)
        val database= FirebaseDatabase.getInstance()
        val myRef = database.getReference("spots")
        root.recyclerView.setHasFixedSize(true)
        root.recyclerView.layoutManager = LinearLayoutManager(activity!!.applicationContext,RecyclerView.VERTICAL,false)

        val spotAdapter = RecyclerSpotAdapter(spotlist,activity!!.applicationContext)
        root.recyclerView.adapter = spotAdapter
        spotAdapter.onItemClick = {it ->
            //Toast.makeText(this.context,it.nombre,Toast.LENGTH_SHORT).show()
            var intent = Intent(this.context,SpotsMapsActivity::class.java)
            intent.putExtra("nombre",it.nombre)
            startActivity(intent)

        }

        myRef.addValueEventListener(object:ValueEventListener{
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                for (snapshot: DataSnapshot in dataSnapshot.children){
                    val spot:Spot ?=  snapshot.getValue(Spot::class.java)
                    spotlist.add(spot!!)
                }
                spotAdapter.notifyDataSetChanged()

            }


            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w("value", "Failed to read value.", error.toException())
            }

        })



        return root
    }


}
