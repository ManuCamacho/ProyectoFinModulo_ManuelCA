package com.example.proyectofinmodulo_manuelca

import android.content.Intent
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity

open class ActivityWithMenus :AppCompatActivity() {
    companion object{
        var actividadActual=0;
    }
    override fun onCreateOptionsMenu(menu: Menu):Boolean {

        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)

        if (menu != null) {
            for (i in 0 until menu.size()) {
                if (i == actividadActual) menu.getItem(i).isEnabled = false
                else menu.getItem(i).isEnabled = true
            }
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){

            // Opcion para mostrar la clase ListadoActivity
            R.id.ListadoJugadores ->{
                val intent = Intent(this,ListadoActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                actividadActual=0;
                startActivity(intent)
                true
            }
            // Opcion para mostrar la clase InicioActivity
            R.id.AnadirJugadores ->{
                val intent = Intent(this,InicioActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                actividadActual=1;
                startActivity(intent)
                true
            }
            // Opcion para mostrar la clase Camara_Galeria
            R.id.AccesoCyG ->{
                val intent = Intent(this,Camara_Galeria::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                actividadActual=2;
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }
}