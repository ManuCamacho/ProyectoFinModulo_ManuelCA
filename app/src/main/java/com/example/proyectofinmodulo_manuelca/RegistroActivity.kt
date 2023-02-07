package com.example.proyectofinmodulo_manuelca

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.proyectofinmodulo_manuelca.databinding.ActivityRegistroBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class RegistroActivity : AppCompatActivity() {
    lateinit var binding: ActivityRegistroBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Establecemos la accion que deseamos cuando pulsamos el Boton Registrar
        binding.BRegistrar.setOnClickListener {
            // Si la campos no estan vacios intentamos registrar el usuario
            if (binding.Enombre.text.isNotEmpty() && binding.Eapellidos.text.isNotEmpty()
                && binding.Eemail.text.isNotEmpty() && binding.Epassword.text.isNotEmpty()) {
                // Si los campos están completos, intentamos registrar al usuario con Firebase
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                    binding.Eemail.text.toString(),
                    binding.Epassword.text.toString()
                ).addOnCompleteListener {
                    if(it.isSuccessful){
                        // Si el registro tiene exito, iniciamos la clase InicioActivity
                        val intent = Intent(this, InicioActivity::class.java).apply {
                            putExtra("nombreusuario",binding.Enombre.text.toString())
                        }
                        startActivity(intent)
                    }else {
                        // Si no se ha creado con exito mostramos el siguiente mensaje
                        Toast.makeText(this, "No se ha creado el usuario", Toast.LENGTH_SHORT).show()}
                }
            } else {
                // Si nos hemos dejado algun campo vacio mostramos el siguiente mensaje
                Toast.makeText(this, "Algun campo esta vacio", Toast.LENGTH_SHORT).show()
            }
        }
    }
}