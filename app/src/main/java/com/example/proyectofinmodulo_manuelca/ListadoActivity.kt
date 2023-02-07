package com.example.proyectofinmodulo_manuelca

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectofinmodulo_manuelca.databinding.ActivityListadoBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ListadoActivity : ActivityWithMenus() {
     lateinit var jugadoresrecyclerView: RecyclerView
     lateinit var JugadoresListado: ArrayList<Listado>
     var db = Firebase.firestore
     lateinit var binding:ActivityListadoBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListadoBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_listado)

        jugadoresrecyclerView = findViewById(R.id.recycler)
        jugadoresrecyclerView.layoutManager = LinearLayoutManager(this)

        JugadoresListado = arrayListOf()
        cargarDatos()

    }
    fun cargarDatos() {

        val db = FirebaseFirestore.getInstance()
        // Obtengo los datos de la base de datos
        db.collection("usuarios")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d("Añadiendo jugadores", "${document.id} => ${document.data}")
                    val zapas = document.toObject(Listado::class.java)
                    JugadoresListado.add(zapas)
                    // Muestro el recyclerView
                    binding.recycler.layoutManager = LinearLayoutManager(this)
                    binding.recycler.adapter = AdapterListado(JugadoresListado)
                    // Vuelvo a actualizar el adapter para el borrado
                    var adapter = AdapterListado(JugadoresListado)
                    jugadoresrecyclerView.adapter = adapter
                    val position = JugadoresListado.indexOf(Listado())
                    adapter.notifyItemRemoved(position)
                    adapter.notifyDataSetChanged()

                }


            }
            .addOnFailureListener { exception ->
                Log.w("Añadiendo Jugadores", "Error al obtener los datos de los Jugadores.", exception)
            }


    }
}


