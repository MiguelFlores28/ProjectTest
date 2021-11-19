package mx.uaa.mafl.projecttest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent

/*Código de la actividad pricipal: Se dedica a cargar recursos como la plash screen y la actividad de login*/

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        Thread.sleep(2000)
        setTheme(R.style.Theme_AppCompat_DayNight_NoActionBar)
        super.onCreate(savedInstanceState)
        startActivity(Intent(this, UserLogin::class.java))
        setContentView(R.layout.layout_user_login)
    }
}

/*Por hacer
* crear DataClass para mostrar características de personajes
*   Parece ser que se requerirá una tabla extra en la BD para almaacenar esto
*
* Crear la lógica del juego contra el CPU
*   Agregarle que el también tenga un personaje
*   Que evalúe las deciciones del jugador
*   Que evalúe sus propias deciciones para saber que personaje tiene
*/