package com.example.loginactivity

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.spot_item.view.*
import java.util.ArrayList

class RecyclerSpotAdapter:RecyclerView.Adapter<RecyclerSpotAdapter.SpotViewHolder> {

    private var listSpot:ArrayList<Spot> = ArrayList()
    private var context : Context?= null
    var onItemClick: ((Spot) -> Unit)? = null

    constructor(listSpot:ArrayList<Spot>, context:Context){
        this.listSpot = listSpot
        this.context = context
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerSpotAdapter.SpotViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.spot_item,parent,false)
        return SpotViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listSpot.size

    }

    override fun onBindViewHolder(holder: RecyclerSpotAdapter.SpotViewHolder, position: Int) {
        var spot = listSpot[position]
        holder.loadItem(spot)
    }


    inner class SpotViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        fun loadItem(spot:Spot){
            itemView.tvNombre.text = "Nombre: "+spot.nombre
            itemView.tvDireccion.text = "Ubicación: "+spot.ubicacion
            itemView.tvModalidad.text = "Modalidad: "+spot.modalidad
            itemView.tvIngreso.text = "ingreso: "+spot.ingreso
            Picasso.get().load(spot.url).into(itemView.iSpot)

        }
        init{
            itemView.setOnClickListener{
                onItemClick?.invoke(listSpot[adapterPosition])
            }
        }
    }
}
