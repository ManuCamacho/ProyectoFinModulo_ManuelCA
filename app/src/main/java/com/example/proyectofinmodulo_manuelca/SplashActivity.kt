package com.example.proyectofinmodulo_manuelca

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.proyectofinmodulo_manuelca.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        val screenSplash= installSplashScreen() // Instala la pantalla splash

        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        screenSplash.setKeepOnScreenCondition{true} // Mantiene la pantalla splash en la pantalla hasta que se cumpla la condición
        Thread.sleep(4000)// Dura 4 segundos
        val intent = Intent(this,MainActivity::class.java)// Crea un Intent
        startActivity(intent)// Inicia la actividad MainActivity
        finish()// Finaliza la actividad MainActivity
    }



}