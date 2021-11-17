package mx.uaa.mafl.projecttest

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Tablero : AppCompatActivity() {

    private lateinit var imgPersonajeActual: ImageView
    /*private lateinit var imgRow11 : ImageView
    private lateinit var imgRow12 : ImageView
    private lateinit var imgRow13 : ImageView
    private lateinit var imgRow14 : ImageView
    private lateinit var imgRow21 : ImageView
    private lateinit var imgRow22 : ImageView
    private lateinit var imgRow23 : ImageView
    private lateinit var imgRow24 : ImageView
    private lateinit var imgRow31 : ImageView
    private lateinit var imgRow32 : ImageView
    private lateinit var imgRow33 : ImageView
    private lateinit var imgRow34 : ImageView
    private lateinit var imgRow41 : ImageView
    private lateinit var imgRow42 : ImageView
    private lateinit var imgRow43 : ImageView
    private lateinit var imgRow44 : ImageView
    private lateinit var imgRow51 : ImageView
    private lateinit var imgRow52 : ImageView
    private lateinit var imgRow53 : ImageView
    private lateinit var imgRow54 : ImageView
    private lateinit var imgRow61 : ImageView
    private lateinit var imgRow62 : ImageView
    private lateinit var imgRow63 : ImageView
    private lateinit var imgRow64 : ImageView*/
    private lateinit var vistaMensajes : RecyclerView
    private lateinit var adapter: RecViewAdapter
    private lateinit var txt : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_tablero)

        var randNum = (1..24).random()
        vistaMensajes = findViewById(R.id.recViewMsj)
        adapter = RecViewAdapter()

        vistaMensajes.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        vistaMensajes.adapter = adapter

        /*En lugar de pasar el numero de personaje, creo que se ahorraría todo el código
        *del when, pasandole directamente la url de la imagen*/

        var personajeNumber:Int = ((intent.getStringExtra("Personaje")).toString()).toInt()
        imgPersonajeActual = findViewById(R.id.imgChrctrGame)
        when (personajeNumber){
            1 ->{ imgPersonajeActual.setImageResource(R.drawable.mario)}
            2 ->{ imgPersonajeActual.setImageResource(R.drawable.bowser)}
            3 ->{ imgPersonajeActual.setImageResource(R.drawable.peach)}
            4 ->{ imgPersonajeActual.setImageResource(R.drawable.samus)}
            5 ->{ imgPersonajeActual.setImageResource(R.drawable.dark_samus)}
            6 ->{ imgPersonajeActual.setImageResource(R.drawable.ridley)}

            7 ->{ imgPersonajeActual.setImageResource(R.drawable.dk)}
            8 ->{ imgPersonajeActual.setImageResource(R.drawable.didykong)}
            9 ->{ imgPersonajeActual.setImageResource(R.drawable.krool)}
            10 ->{ imgPersonajeActual.setImageResource(R.drawable.pikachu)}
            11 ->{ imgPersonajeActual.setImageResource(R.drawable.greninja)}
            12 ->{ imgPersonajeActual.setImageResource(R.drawable.jigglypuff)}

            13 ->{ imgPersonajeActual.setImageResource(R.drawable.kirby)}
            14 ->{ imgPersonajeActual.setImageResource(R.drawable.dedede)}
            15 ->{ imgPersonajeActual.setImageResource(R.drawable.mknight)}
            16 ->{ imgPersonajeActual.setImageResource(R.drawable.lucina)}
            17 ->{ imgPersonajeActual.setImageResource(R.drawable.roy)}
            18 ->{ imgPersonajeActual.setImageResource(R.drawable.ike)}

            19 ->{ imgPersonajeActual.setImageResource(R.drawable.hyrulelink)}
            20 ->{ imgPersonajeActual.setImageResource(R.drawable.zelda)}
            21 ->{ imgPersonajeActual.setImageResource(R.drawable.ganondorf)}
            22 ->{ imgPersonajeActual.setImageResource(R.drawable.foxmcloud)}
            23 ->{ imgPersonajeActual.setImageResource(R.drawable.falco)}
            24 ->{ imgPersonajeActual.setImageResource(R.drawable.wolf)}

        }
        
    }
}