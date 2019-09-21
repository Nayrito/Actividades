package com.example.loginactivity


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_store.view.*
import kotlinx.android.synthetic.main.spot_item.view.*


class StoreFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var root = inflater.inflate(R.layout.fragment_store, container, false)

        val url_onboard = "https://firebasestorage.googleapis.com/v0/b/skatemedallo-9e249.appspot.com/o/tiendas%2Fonboard.jpg?alt=media&token=211a26b1-8a7b-4b2e-a4f9-e29d9c9bb4a8"
        val url_bunker = "https://firebasestorage.googleapis.com/v0/b/skatemedallo-9e249.appspot.com/o/tiendas%2Fbunker.jpg?alt=media&token=7f6cc6f2-7b94-4847-852e-a5ff920af84a"
        Picasso.get().load(url_bunker).into(root.ibBunker)
        Picasso.get().load(url_onboard).into(root.ibOnboard)

        root.ibOnboard.setOnClickListener{
            var intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://onboardsk8.com"))
            startActivity(intent)
            Toast.makeText(this.context,"Entrando a On Board Skate Shop",Toast.LENGTH_SHORT).show()

        }
        root.ibBunker.setOnClickListener{
            var intent = Intent(Intent.ACTION_VIEW, Uri.parse("http://www.bunkerskateshop.com/"))
            startActivity(intent)
            Toast.makeText(this.context,"Entrando a Bunker Skate Shop",Toast.LENGTH_SHORT).show()

        }

        return root
    }


}
