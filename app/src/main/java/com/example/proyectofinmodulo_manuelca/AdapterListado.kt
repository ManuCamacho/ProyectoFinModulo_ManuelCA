package com.example.proyectofinmodulo_manuelca

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class AdapterListado(private var userList: ArrayList<Listado>):
    RecyclerView.Adapter<JugadoresViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JugadoresViewHolder {
       val itemView = LayoutInflater.from(parent.context).inflate(R.layout.listado_item,parent,false)
        return  JugadoresViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: JugadoresViewHolder, position: Int) {

        val item = userList[position]
        holder.render(item)

    }

    override fun getItemCount(): Int {
        return userList.size
    }


}