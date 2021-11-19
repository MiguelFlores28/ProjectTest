package mx.uaa.mafl.projecttest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class GuiaJuego : AppCompatActivity() {

    private lateinit var  btn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_guia_juego)

        btn = findViewById(R.id.btnRegresoInicio)

        startActivity(Intent(this, MenuPrincipal::class.java))
    }
}