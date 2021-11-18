package mx.uaa.mafl.projecttest

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Tablero : AppCompatActivity() {

    private lateinit var imgPersonajeActual: ImageView
    private lateinit var imgRow11 : ImageButton
    private lateinit var imgRow12 : ImageButton
    private lateinit var imgRow13 : ImageButton
    private lateinit var imgRow14 : ImageButton
    private lateinit var imgRow21 : ImageButton
    private lateinit var imgRow22 : ImageButton
    private lateinit var imgRow23 : ImageButton
    private lateinit var imgRow24 : ImageButton
    private lateinit var imgRow31 : ImageButton
    private lateinit var imgRow32 : ImageButton
    private lateinit var imgRow33 : ImageButton
    private lateinit var imgRow34 : ImageButton
    private lateinit var imgRow41 : ImageButton
    private lateinit var imgRow42 : ImageButton
    private lateinit var imgRow43 : ImageButton
    private lateinit var imgRow44 : ImageButton
    private lateinit var imgRow51 : ImageButton
    private lateinit var imgRow52 : ImageButton
    private lateinit var imgRow53 : ImageButton
    private lateinit var imgRow54 : ImageButton
    private lateinit var imgRow61 : ImageButton
    private lateinit var imgRow62 : ImageButton
    private lateinit var imgRow63 : ImageButton
    private lateinit var imgRow64 : ImageButton
    private lateinit var vistaMensajes : RecyclerView
    private lateinit var adapter: RecViewAdapter
    private lateinit var txt : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_tablero)

        var randNum = (1..24).random()
        var personajeNumber:Int = ((intent.getStringExtra("Personaje")).toString()).toInt()
        var numTableroRand = (1..4).random()

        imgPersonajeActual = findViewById(R.id.imgChrctrGame)
        vistaMensajes = findViewById(R.id.recViewMsj)
        adapter = RecViewAdapter()
        vistaMensajes.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        vistaMensajes.adapter = adapter

        imgRow11 = findViewById(R.id.row1_1)
        imgRow12 = findViewById(R.id.row1_2)
        imgRow13 = findViewById(R.id.row1_3)
        imgRow14 = findViewById(R.id.row1_4)
        imgRow21 = findViewById(R.id.row2_1)
        imgRow22 = findViewById(R.id.row2_2)

        imgRow23 = findViewById(R.id.row2_3)
        imgRow24 = findViewById(R.id.row2_4)
        imgRow31 = findViewById(R.id.row3_1)
        imgRow32 = findViewById(R.id.row3_2)
        imgRow33 = findViewById(R.id.row3_3)
        imgRow34 = findViewById(R.id.row3_4)

        imgRow41 = findViewById(R.id.row4_1)
        imgRow42 = findViewById(R.id.row4_2)
        imgRow43 = findViewById(R.id.row4_3)
        imgRow44 = findViewById(R.id.row4_4)
        imgRow51 = findViewById(R.id.row5_1)
        imgRow52 = findViewById(R.id.row5_2)

        imgRow53 = findViewById(R.id.row5_3)
        imgRow54 = findViewById(R.id.row5_4)
        imgRow61 = findViewById(R.id.row6_1)
        imgRow62 = findViewById(R.id.row6_2)
        imgRow63 = findViewById(R.id.row6_3)
        imgRow64 = findViewById(R.id.row6_4)

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
        generarTablero(numTableroRand)
        
    }

    fun generarTablero(numeroTablero: Int){
        when (numeroTablero){
            1 ->{
                imgRow11.setImageResource(R.drawable.zelda)
                imgRow12.setImageResource(R.drawable.kirby)
                imgRow13.setImageResource(R.drawable.pikachu)
                imgRow14.setImageResource(R.drawable.dk)
                imgRow21.setImageResource(R.drawable.falco)
                imgRow22.setImageResource(R.drawable.mario)

                imgRow23.setImageResource(R.drawable.roy)
                imgRow24.setImageResource(R.drawable.didykong)
                imgRow31.setImageResource(R.drawable.samus)
                imgRow32.setImageResource(R.drawable.ike)
                imgRow33.setImageResource(R.drawable.foxmcloud)
                imgRow34.setImageResource(R.drawable.peach)

                imgRow41.setImageResource(R.drawable.lucina)
                imgRow42.setImageResource(R.drawable.hyrulelink)
                imgRow43.setImageResource(R.drawable.bowser)
                imgRow44.setImageResource(R.drawable.dark_samus)
                imgRow51.setImageResource(R.drawable.mknight)
                imgRow52.setImageResource(R.drawable.krool)

                imgRow53.setImageResource(R.drawable.wolf)
                imgRow54.setImageResource(R.drawable.ridley)
                imgRow61.setImageResource(R.drawable.ganondorf)
                imgRow62.setImageResource(R.drawable.dedede)
                imgRow63.setImageResource(R.drawable.greninja)
                imgRow64.setImageResource(R.drawable.jigglypuff)
            }
            2 ->{
                imgRow11.setImageResource(R.drawable.ridley)
                imgRow12.setImageResource(R.drawable.peach)
                imgRow13.setImageResource(R.drawable.krool)
                imgRow14.setImageResource(R.drawable.roy)
                imgRow21.setImageResource(R.drawable.wolf)
                imgRow22.setImageResource(R.drawable.lucina)

                imgRow23.setImageResource(R.drawable.didykong)
                imgRow24.setImageResource(R.drawable.bowser)
                imgRow31.setImageResource(R.drawable.mario)
                imgRow32.setImageResource(R.drawable.pikachu)
                imgRow33.setImageResource(R.drawable.foxmcloud)
                imgRow34.setImageResource(R.drawable.dedede)

                imgRow41.setImageResource(R.drawable.dk)
                imgRow42.setImageResource(R.drawable.mknight)
                imgRow43.setImageResource(R.drawable.jigglypuff)
                imgRow44.setImageResource(R.drawable.greninja)
                imgRow51.setImageResource(R.drawable.samus)
                imgRow52.setImageResource(R.drawable.dark_samus)

                imgRow53.setImageResource(R.drawable.zelda)
                imgRow54.setImageResource(R.drawable.hyrulelink)
                imgRow61.setImageResource(R.drawable.kirby)
                imgRow62.setImageResource(R.drawable.ike)
                imgRow63.setImageResource(R.drawable.ganondorf)
                imgRow64.setImageResource(R.drawable.falco)
            }
            3 ->{
                imgRow11.setImageResource(R.drawable.mario)
                imgRow12.setImageResource(R.drawable.peach)
                imgRow13.setImageResource(R.drawable.bowser)
                imgRow14.setImageResource(R.drawable.samus)
                imgRow21.setImageResource(R.drawable.dark_samus)
                imgRow22.setImageResource(R.drawable.ridley)

                imgRow23.setImageResource(R.drawable.dk)
                imgRow24.setImageResource(R.drawable.didykong)
                imgRow31.setImageResource(R.drawable.krool)
                imgRow32.setImageResource(R.drawable.pikachu)
                imgRow33.setImageResource(R.drawable.greninja)
                imgRow34.setImageResource(R.drawable.jigglypuff)

                imgRow41.setImageResource(R.drawable.kirby)
                imgRow42.setImageResource(R.drawable.dedede)
                imgRow43.setImageResource(R.drawable.mknight)
                imgRow44.setImageResource(R.drawable.lucina)
                imgRow51.setImageResource(R.drawable.roy)
                imgRow52.setImageResource(R.drawable.ike)

                imgRow53.setImageResource(R.drawable.hyrulelink)
                imgRow54.setImageResource(R.drawable.zelda)
                imgRow61.setImageResource(R.drawable.ganondorf)
                imgRow62.setImageResource(R.drawable.foxmcloud)
                imgRow63.setImageResource(R.drawable.falco)
                imgRow64.setImageResource(R.drawable.wolf)
            }
            4 ->{
                imgRow11.setImageResource(R.drawable.ike)
                imgRow12.setImageResource(R.drawable.mario)
                imgRow13.setImageResource(R.drawable.samus)
                imgRow14.setImageResource(R.drawable.dk)
                imgRow21.setImageResource(R.drawable.kirby)
                imgRow22.setImageResource(R.drawable.zelda)

                imgRow23.setImageResource(R.drawable.pikachu)
                imgRow24.setImageResource(R.drawable.mknight)
                imgRow31.setImageResource(R.drawable.lucina)
                imgRow32.setImageResource(R.drawable.falco)
                imgRow33.setImageResource(R.drawable.hyrulelink)
                imgRow34.setImageResource(R.drawable.bowser)

                imgRow41.setImageResource(R.drawable.greninja)
                imgRow42.setImageResource(R.drawable.dedede)
                imgRow43.setImageResource(R.drawable.ridley)
                imgRow44.setImageResource(R.drawable.dark_samus)
                imgRow51.setImageResource(R.drawable.didykong)
                imgRow52.setImageResource(R.drawable.wolf)

                imgRow53.setImageResource(R.drawable.ganondorf)
                imgRow54.setImageResource(R.drawable.jigglypuff)
                imgRow61.setImageResource(R.drawable.krool)
                imgRow62.setImageResource(R.drawable.peach)
                imgRow63.setImageResource(R.drawable.foxmcloud)
                imgRow64.setImageResource(R.drawable.roy)
            }

        }
    }
}