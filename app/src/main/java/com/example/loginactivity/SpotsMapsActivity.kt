package com.example.loginactivity

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class SpotsMapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private var key:String ?= null

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spots_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        var bundle = intent.extras
        var nombre = bundle?.getString("nombre")
        key = nombre


    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.uiSettings.isZoomControlsEnabled = true
        mMap.mapType = GoogleMap.MAP_TYPE_NORMAL

        if (key.equals("Niquia Skate Plaza")){
            val niquia = LatLng(6.336127, -75.546620)
            mMap.addMarker(MarkerOptions().position(niquia).title(key))
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(niquia,15.0f))

        }
        if(key.equals("Madera")){
            val madera = LatLng(6.3145627,-75.5586005)
            mMap.addMarker(MarkerOptions().position(madera).title(key))
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(madera,15.0f))

        }
        if(key.equals("Skatepark el Salado")){
            val salado = LatLng(6.1397365,-75.5771171)
            mMap.addMarker(MarkerOptions().position(salado).title(key))
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(salado,15.0f))


        }
        if(key.equals("Skatepark la 4 Sur")){
            val sur = LatLng(6.2041763,-75.5822995)
            mMap.addMarker(MarkerOptions().position(sur).title(key))
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sur,15.0f))
        }
    }
    private fun setUpMap() {
        if(ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE)
            return
        }
        mMap.isMyLocationEnabled = true
    }
    companion object{
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
    }
}
