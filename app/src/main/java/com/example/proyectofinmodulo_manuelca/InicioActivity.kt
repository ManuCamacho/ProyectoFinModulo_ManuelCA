package com.example.proyectofinmodulo_manuelca

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectofinmodulo_manuelca.databinding.ActivityInicioBinding
import com.example.proyectofinmodulo_manuelca.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class InicioActivity : ActivityWithMenus() {
    lateinit var binding: ActivityInicioBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityInicioBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Instanciamos la Base de Datos
        val db = FirebaseFirestore.getInstance()

        // Accion que sucede cuando pulsamos el boton Cerrar Sesion

        binding.BCerrarSesion.setOnClickListener(){
            FirebaseAuth.getInstance().signOut()
            startActivity(Intent(this, MainActivity::class.java))
        }

        // Accion que sucede cuando pulsamos el boton Editar Jugador
        binding.BTEditarJugador.setOnClickListener{
            db.collection("usuarios")
                .whereEqualTo("nombre",binding.EDNombre.text.toString())
                .get().addOnSuccessListener {
                    it.forEach{
                        binding.EDNombre.setText(it.get("nombre") as String?)
                        binding.EDApellidos.setText(it.get("apellidos") as String?)
                        binding.EDPosicion.setText(it.get("posicion") as String?)
                        binding.EDEdad.setText(it.get("edad") as String?)
                    }
                }
        }

        // Accion que sucede cuando pulsamos el boton Guardar Jugador

        binding.BTGuardarJugador.setOnClickListener{
            if (!binding.EDNombre.text.toString().isNullOrEmpty() &&
                !binding.EDApellidos.text.toString().isNullOrEmpty() &&
                !binding.EDEdad.text.toString().isNullOrEmpty() &&
                !binding.EDPosicion.text.toString().isNullOrEmpty()){

                db.collection("usuarios").document(binding.EDNombre.text.toString())
                    .set(mapOf(
                        "nombre" to binding.EDNombre.text.toString(),
                        "apellidos" to binding.EDApellidos.text.toString(),
                        "posicion" to binding.EDPosicion.text.toString(),
                        "edad" to binding.EDEdad.text.toString()
                    ))
                actualizarDatos()


            }else{
                Toast.makeText(this,"Algun campo esta vacio", Toast.LENGTH_SHORT).show()}
        }

        // Accion que sucede cuando pulsamos el boton Eliminar Jugador

        binding.BTEliminarJugador.setOnClickListener{
            db.collection("usuarios")
                .document(binding.EDNombre.text.toString())
                .delete()
            actualizarDatos()
        }


    }
    // Funcion para actualizar los datos despues de introducir un nuevo jugador
    fun actualizarDatos() {
        val recyclerView = findViewById<RecyclerView>(R.id.recycler)
        val firestore = FirebaseFirestore.getInstance()

        firestore.collection("usuarios").get()
            .addOnSuccessListener { result ->
                val datos = result.toObjects(Listado::class.java)
                recyclerView.adapter = AdapterListado(datos as ArrayList<Listado>)
            }
    }

}