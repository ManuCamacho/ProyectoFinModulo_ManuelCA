package com.example.proyectofinmodulo_manuelca

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.proyectofinmodulo_manuelca.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.BIniciar.setOnClickListener{
            // Al pulsar sobre el boton INICIAR SESION, comprobamos autentificacion
            // Pasandole a FireBase el correo y la contraseña introducida
            login()
        }

        binding.BRegistrar.setOnClickListener{
            startActivity(Intent(this, RegistroActivity::class.java))
        }

    }
    private fun login(){

        if(binding.EmailAddress.text.isNotEmpty() && binding.TextPassword.text.isNotEmpty()) {
            FirebaseAuth.getInstance().signInWithEmailAndPassword(
                binding.EmailAddress.text.toString(),
                binding.TextPassword.text.toString()
            )
                .addOnCompleteListener {
                    if(it.isSuccessful){
                        val intent = Intent(this,ListadoActivity::class.java)
                        startActivity(intent)
                    }else{
                        Toast.makeText(this,"Correo o password incorrecto", Toast.LENGTH_SHORT).show()
                    }
                }
        }else{Toast.makeText(this,"Algun campo esta vacio",Toast.LENGTH_SHORT).show()}
    }
}