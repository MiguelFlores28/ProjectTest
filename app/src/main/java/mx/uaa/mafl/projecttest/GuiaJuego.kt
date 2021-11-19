package mx.uaa.mafl.projecttest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

/*Código que nos muestra la guiía del juego*/

class GuiaJuego : AppCompatActivity() {

    private lateinit var  btn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_guia_juego)

        /*Botón para regresar al menú principal*/
        btn = findViewById(R.id.btnRegresoInicio)

        btn.setOnClickListener(){
            setContentView(R.layout.layout_menu_principal)
            startActivity(Intent(this, MenuPrincipal::class.java))
        }
    }
}