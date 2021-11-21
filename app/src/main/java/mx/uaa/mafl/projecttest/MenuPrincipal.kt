package mx.uaa.mafl.projecttest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

/*Código que nos muestra el menú principal, nos dirije a las actividades de:
* Guía de juego
* Juego contra un jugador
* Juego contra Computadora*/

class MenuPrincipal : AppCompatActivity() {

    //Definición de recursos y variables
    private var backPressedTime = 0L
    private lateinit var btnJVJ : Button
    private lateinit var btnJVC : Button
    private lateinit var btnGuia : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_menu_principal)
        //personajeApp = this as PersonajeApp
        //Definición de recursos y variables
        btnJVJ = findViewById(R.id.btnPVP)
        btnJVC = findViewById(R.id.btnPVC)
        btnGuia = findViewById(R.id.btnGuia)

        //A la actividad del juego contra máquina
        btnJVC.setOnClickListener {
            startActivity(Intent(this, CharSelect::class.java))
        }

        //A la actividad de guía
       btnGuia.setOnClickListener {
           val intent = Intent(this@MenuPrincipal, GuiaJuego::class.java).apply {
               putExtra("band","guia")
           }
           startActivity(intent)
       }

        btnJVJ.setOnClickListener{
            val intent = Intent(this@MenuPrincipal, GuiaJuego::class.java).apply {
                putExtra("band","pvp")
            }
            startActivity(intent)
        }
        //Agregar funcionalidad al botón de jugador contra jugador

    }

    /*Botón atrás, se ocupan dos clics para salir*/
    override fun onBackPressed() {
        if(backPressedTime + 2000 > System.currentTimeMillis()){ super.onBackPressed() }
        else{ Toast.makeText(this, "Presiona atrás otra vez para salir", Toast.LENGTH_LONG).show() }
        backPressedTime = System.currentTimeMillis()
    }



}
