package com.example.proyectofinmodulo_manuelca

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.proyectofinmodulo_manuelca.databinding.ListadoItemBinding

class JugadoresViewHolder(view: android.view.View):RecyclerView.ViewHolder(view) {

    val binding = ListadoItemBinding.bind(view)

    fun render(jugadoresModel: Listado) {
        binding.TNombre.text = "Nombre: " + jugadoresModel.nombre
        binding.TApellidos.text = "Apellidos: " + jugadoresModel.apellidos
        binding.TEdad.text = "Edad: " + jugadoresModel.edad
        binding.TPosicion.text = "Posicion: "+ jugadoresModel.posicion
        Glide.with(binding.Foto.context).load(jugadoresModel.Imagen).into(binding.Foto)

    }
}