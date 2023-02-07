package com.example.proyectofinmodulo_manuelca

import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ImageButton
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import com.example.proyectofinmodulo_manuelca.databinding.ActivityCamaraGaleriaBinding
import com.example.proyectofinmodulo_manuelca.databinding.ActivityMainBinding

class Camara_Galeria : ActivityWithMenus() {
    lateinit var imagen: ImageButton
    lateinit var binding: ActivityCamaraGaleriaBinding
    val pickFoto = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        val image = it.data?.extras?.get("data") as Bitmap
        binding.imageButton.setImageBitmap(image)
    }
    val pickMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) {
        // Devuelve la uri de la imagen seleccionada
            uri ->
        if (uri != null) {
            // Imagen seleccionada
            imagen.setImageURI(uri)
        } else {
            // No se ha seleccionado ninguna imagen
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityCamaraGaleriaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        imagen = binding.imageButton

        // Cuando pulsemos el boton galeria nos abre la galeria de fotos
        binding.BTAccesoGaleria.setOnClickListener {
            pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))

        // Cuando pulsemos el boton camara nos abre la camara de fotos
            binding.BTAccesoACamara.setOnClickListener {
                pickFoto.launch(Intent(MediaStore.ACTION_IMAGE_CAPTURE))
            }

        }
    }
}