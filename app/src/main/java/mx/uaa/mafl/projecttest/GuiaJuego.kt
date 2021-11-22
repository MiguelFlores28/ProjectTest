package mx.uaa.mafl.projecttest

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.firebase.database.FirebaseDatabase

/*Código que nos muestra la guiía del juego*/

class GuiaJuego : AppCompatActivity() {

    private lateinit var  btn : Button
    private var bandera = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_guia_juego)

        /*Botón para regresar al menú principal*/
        btn = findViewById(R.id.btnRegresoInicio)

        val extras = intent.extras
        if(extras != null) {
            bandera = extras.getString("band").toString()
        }
        btn.setOnClickListener(){
            if(bandera == "guia") {
                setContentView(R.layout.layout_menu_principal)
                startActivity(Intent(this, MenuPrincipal::class.java))
            }else{
                startActivity(Intent(this, HomeActivity::class.java))
            }
        }
    }

}