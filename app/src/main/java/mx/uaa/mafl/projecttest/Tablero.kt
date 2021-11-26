package mx.uaa.mafl.projecttest

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*

class Tablero : AppCompatActivity() {

    //Definición de variables y recursos
    private lateinit var botonTamano1 : Button
    private lateinit var botonTamano2 : Button
    private lateinit var botonTamano3 : Button
    private lateinit var botonTamano4 : Button
    private lateinit var botonCombate1 : Button
    private lateinit var botonCombate2 : Button
    private lateinit var botonCombate3 : Button
    private lateinit var botonFranq1 :Button
    private lateinit var botonFranq2 :Button
    private lateinit var botonFranq3 :Button
    private lateinit var botonFranq4 :Button
    private lateinit var botonFranq5 :Button
    private lateinit var botonFranq6 :Button
    private lateinit var botonFranq7 :Button
    private lateinit var botonFranq8 :Button
    private lateinit var botonpreg1 : Button
    private lateinit var botonpreg2 : Button
    private lateinit var botonpreg3 : Button
    private lateinit var botonpreg4 : Button
    private lateinit var botonpreg5 : Button
    private lateinit var botonpreg6 : Button
    private lateinit var botonpreg7 : Button
    private lateinit var botonpreg8 : Button
    private lateinit var botonpreg9 : Button
    private lateinit var botonpreg10 : Button
    private lateinit var botonpreg11 : Button
    private lateinit var botonpreg12 : Button
    private lateinit var botonpreg13 : Button
    private lateinit var botonpreg14 : Button
    private lateinit var botonpreg15 : Button
    private lateinit var botonpreg16 : Button
    private lateinit var botonpreg17 : Button
    private lateinit var botonpreg18 : Button
    private lateinit var botonpreg19 : Button
    private lateinit var botonpreg20 : Button
    private lateinit var botonpreg21 : Button
    private lateinit var botonpreg22 : Button
    private lateinit var botonpreg23 : Button
    private lateinit var botonpreg24 : Button
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
    private lateinit var button : Button
    private var personajeNumber = 0
    private var numTableroRand = 0
    var listaPersonaje  = arrayListOf<Personaje>()
    private var tempstring = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_tablero)
        val prefs = this.getSharedPreferences("ProyectoPrefs", Context.MODE_PRIVATE)
        val username = prefs.getString("username","")
        //Definición de variables y recursos, recuperación del personaje desde la actividad CharSelect
        numTableroRand = (1..4).random()
        personajeNumber = (1..24).random()
        imgPersonajeActual = findViewById(R.id.imgChrctrGame)
        button = findViewById(R.id.btnEnviarMsj)
        setupTablero()
        setupBotonesPreguntas()
        setupArrayList()
        enableButtons()
    }
    private fun setupArrayList(){
        listaPersonaje.add(Personaje(1, "mario", 1, 2, 1))
        listaPersonaje.add(Personaje(2, "bowser", 1, 4, 1))
        listaPersonaje.add(Personaje(3, "peach", 1, 2, 2))
        listaPersonaje.add(Personaje(4, "samus", 2, 3, 2))
        listaPersonaje.add(Personaje(5, "darksamus", 2, 3, 2))
        listaPersonaje.add(Personaje(6, "ridley", 2, 4, 1))
        listaPersonaje.add(Personaje(7, "dk", 3, 4, 1))
        listaPersonaje.add(Personaje(8, "didykong", 3, 1, 1))
        listaPersonaje.add(Personaje(9, "krool", 3, 4, 2))
        listaPersonaje.add(Personaje(10, "pikachu", 4, 1, 2))
        listaPersonaje.add(Personaje(11, "greninja", 4, 2, 1))
        listaPersonaje.add(Personaje(12, "jigglypuff", 4, 1, 1))
        listaPersonaje.add(Personaje(13, "kirby", 5, 1, 1))
        listaPersonaje.add(Personaje(14, "dedede", 5, 4, 3))
        listaPersonaje.add(Personaje(15, "mknight", 5, 1, 3))
        listaPersonaje.add(Personaje(16, "lucina", 6, 2, 3))
        listaPersonaje.add(Personaje(17, "roy", 6, 2, 3))
        listaPersonaje.add(Personaje(18, "ike", 6, 3, 3))
        listaPersonaje.add(Personaje(19, "link", 7, 2, 3))
        listaPersonaje.add(Personaje(20, "zelda", 7, 2, 2))
        listaPersonaje.add(Personaje(21, "ganondorf", 7, 4, 1))
        listaPersonaje.add(Personaje(22, "fox", 8, 2, 1))
        listaPersonaje.add(Personaje(23, "falco", 8, 2, 2))
        listaPersonaje.add(Personaje(24, "wolf", 8, 2, 1))
    }
    private fun setupBotonesPreguntas() {
        botonTamano1 = findViewById(R.id.botonTamano1)
        botonTamano2 = findViewById(R.id.botonTamano2)
        botonTamano3 = findViewById(R.id.botonTamano3)
        botonTamano4 = findViewById(R.id.botonTamano4)
        botonFranq1 = findViewById(R.id.botonFranq1)
        botonFranq2 = findViewById(R.id.botonFranq2)
        botonFranq3 = findViewById(R.id.botonFranq3)
        botonFranq4 = findViewById(R.id.botonFranq4)
        botonFranq5 = findViewById(R.id.botonFranq5)
        botonFranq6 = findViewById(R.id.botonFranq6)
        botonFranq7 = findViewById(R.id.botonFranq7)
        botonFranq8 = findViewById(R.id.botonFranq8)
        botonCombate1 = findViewById(R.id.botonCombate1)
        botonCombate2 = findViewById(R.id.botonCombate2)
        botonCombate3 = findViewById(R.id.botonCombate3)
        botonpreg1 = findViewById(R.id.botonpreg1)
        botonpreg2 = findViewById(R.id.botonpreg2)
        botonpreg3 = findViewById(R.id.botonpreg3)
        botonpreg4 = findViewById(R.id.botonpreg4)
        botonpreg5 = findViewById(R.id.botonpreg5)
        botonpreg6 = findViewById(R.id.botonpreg6)
        botonpreg7 = findViewById(R.id.botonpreg7)
        botonpreg8 = findViewById(R.id.botonpreg8)
        botonpreg9 = findViewById(R.id.botonpreg9)
        botonpreg10 = findViewById(R.id.botonpreg10)
        botonpreg11 = findViewById(R.id.botonpreg11)
        botonpreg12 = findViewById(R.id.botonpreg12)
        botonpreg13 = findViewById(R.id.botonpreg13)
        botonpreg14 = findViewById(R.id.botonpreg14)
        botonpreg15 = findViewById(R.id.botonpreg15)
        botonpreg16 = findViewById(R.id.botonpreg16)
        botonpreg17 = findViewById(R.id.botonpreg17)
        botonpreg18 = findViewById(R.id.botonpreg18)
        botonpreg19 = findViewById(R.id.botonpreg19)
        botonpreg20 = findViewById(R.id.botonpreg20)
        botonpreg21 = findViewById(R.id.botonpreg21)
        botonpreg22 = findViewById(R.id.botonpreg22)
        botonpreg23 = findViewById(R.id.botonpreg23)
        botonpreg24 = findViewById(R.id.botonpreg24)
        //eventos botones
        botonTamano1.setOnClickListener {
            tempstring = "Mini"
                preguntarPersonajeTamano1(1)
        }
        botonTamano2.setOnClickListener {
            tempstring = "Pequeño"
                preguntarPersonajeTamano1(2)
        }
        botonTamano3.setOnClickListener {
            tempstring = "Mediano"
                preguntarPersonajeTamano1(3)
        }
        botonTamano4.setOnClickListener {
            tempstring = "Grande"
                preguntarPersonajeTamano1(4)
        }

        botonFranq1.setOnClickListener {
            tempstring = "Mario"
                preguntarPersonajeFranq1(1)
        }
        botonFranq2.setOnClickListener {
            tempstring = "Metroid"
                preguntarPersonajeFranq1(2)
        }
        botonFranq3.setOnClickListener {
            tempstring = "DK"
                preguntarPersonajeFranq1(3)
        }
        botonFranq4.setOnClickListener {
            tempstring = "Pokemon"
                preguntarPersonajeFranq1(4)
        }
        botonFranq5.setOnClickListener {
            tempstring = "Kirby"
                preguntarPersonajeFranq1(5)
        }
        botonFranq6.setOnClickListener {
            tempstring = "Fire Emblem"
                preguntarPersonajeFranq1(6)
        }
        botonFranq7.setOnClickListener {
            tempstring = "Zelda"
                preguntarPersonajeFranq1(7)
        }
        botonFranq8.setOnClickListener {
            tempstring = "StarFox"
            preguntarPersonajeFranq1(8)

        }
        botonCombate1.setOnClickListener {

                preguntarPersonajeCombate1(3)

            }
            botonCombate2.setOnClickListener {
                tempstring = "Proyectiles"
                preguntarPersonajeCombate1(2)
            }
            botonCombate3.setOnClickListener {
                tempstring = "Golpes"
                preguntarPersonajeCombate1(1)
            }
            botonpreg1.setOnClickListener {

                preguntarPersonaje1("mario")

            }
            botonpreg2.setOnClickListener {

                preguntarPersonaje1("bowser")

            }
            botonpreg3.setOnClickListener {

                preguntarPersonaje1("peach")

            }
            botonpreg4.setOnClickListener {

                preguntarPersonaje1("samus")

            }
            botonpreg5.setOnClickListener {

                preguntarPersonaje1("darksamus")

            }
            botonpreg6.setOnClickListener {

                preguntarPersonaje1("ridley")

            }
            botonpreg7.setOnClickListener {

                preguntarPersonaje1("dk")

            }
            botonpreg8.setOnClickListener {

                preguntarPersonaje1("didykong")

            }
            botonpreg9.setOnClickListener {

                preguntarPersonaje1("krool")

            }
            botonpreg10.setOnClickListener {
                preguntarPersonaje1("pikachu")
            }
            botonpreg11.setOnClickListener {

                preguntarPersonaje1("greninja")

            }
            botonpreg12.setOnClickListener {
                preguntarPersonaje1("jigglypuff")
            }
            botonpreg13.setOnClickListener {
                preguntarPersonaje1("kirby")

            }
            botonpreg14.setOnClickListener {

                preguntarPersonaje1("dedede")
            }
            botonpreg15.setOnClickListener {
                preguntarPersonaje1("mknight")
            }
            botonpreg16.setOnClickListener {
                preguntarPersonaje1("lucina")

            }
            botonpreg17.setOnClickListener {
                preguntarPersonaje1("roy")
            }
            botonpreg18.setOnClickListener {
                preguntarPersonaje1("ike")
            }
            botonpreg19.setOnClickListener {
                preguntarPersonaje1("link")
            }
            botonpreg20.setOnClickListener {
                preguntarPersonaje1("zelda")
            }
            botonpreg21.setOnClickListener {
                preguntarPersonaje1("ganondorf")
            }
            botonpreg22.setOnClickListener {
                preguntarPersonaje1("fox")
            }
            botonpreg23.setOnClickListener {
                preguntarPersonaje1("falco")
            }
            botonpreg24.setOnClickListener {
                preguntarPersonaje1("wolf")
            }
        }

        private fun enableButtons() {
            botonTamano1.isEnabled = true
            botonTamano2.isEnabled = true
            botonTamano3.isEnabled = true
            botonTamano4.isEnabled = true
            botonFranq1.isEnabled = true
            botonFranq2.isEnabled = true
            botonFranq3.isEnabled = true
            botonFranq4.isEnabled = true
            botonFranq5.isEnabled = true
            botonFranq6.isEnabled = true
            botonFranq7.isEnabled = true
            botonFranq8.isEnabled = true
            botonCombate1.isEnabled = true
            botonCombate2.isEnabled = true
            botonCombate3.isEnabled = true
            botonpreg1.isEnabled = true
            botonpreg2.isEnabled = true
            botonpreg3.isEnabled = true
            botonpreg4.isEnabled = true
            botonpreg5.isEnabled = true
            botonpreg6.isEnabled = true
            botonpreg7.isEnabled = true
            botonpreg8.isEnabled = true
            botonpreg9.isEnabled = true
            botonpreg10.isEnabled = true
            botonpreg11.isEnabled = true
            botonpreg12.isEnabled = true
            botonpreg13.isEnabled = true
            botonpreg14.isEnabled = true
            botonpreg15.isEnabled = true
            botonpreg16.isEnabled = true
            botonpreg17.isEnabled = true
            botonpreg18.isEnabled = true
            botonpreg19.isEnabled = true
            botonpreg20.isEnabled = true
            botonpreg21.isEnabled = true
            botonpreg22.isEnabled = true
            botonpreg23.isEnabled = true
            botonpreg24.isEnabled = true
        }

        private fun setupTablero() {
            //Definición de variables y recursos
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

            imgPersonajeActual.setImageResource(R.drawable.smashball)
            //Llamada a la función de generar tablero, la cual nos generará un tablero de forma aleatoria
            generarTablero(numTableroRand)

            //Código para que al ser presionadas, las casillas "bajen" y cambien por el reverso de la "carta"
            imgRow11.setOnClickListener() { Thread.sleep(500); imgRow11.setImageResource(R.drawable.smashball) }
            imgRow12.setOnClickListener() { Thread.sleep(500); imgRow12.setImageResource(R.drawable.smashball) }
            imgRow13.setOnClickListener() { Thread.sleep(500); imgRow13.setImageResource(R.drawable.smashball) }
            imgRow14.setOnClickListener() { Thread.sleep(500); imgRow14.setImageResource(R.drawable.smashball) }
            imgRow21.setOnClickListener() { Thread.sleep(500); imgRow21.setImageResource(R.drawable.smashball) }
            imgRow22.setOnClickListener() { Thread.sleep(500); imgRow22.setImageResource(R.drawable.smashball) }

            imgRow23.setOnClickListener() { Thread.sleep(500); imgRow23.setImageResource(R.drawable.smashball) }
            imgRow24.setOnClickListener() { Thread.sleep(500); imgRow24.setImageResource(R.drawable.smashball) }
            imgRow31.setOnClickListener() { Thread.sleep(500); imgRow31.setImageResource(R.drawable.smashball) }
            imgRow32.setOnClickListener() { Thread.sleep(500); imgRow32.setImageResource(R.drawable.smashball) }
            imgRow33.setOnClickListener() { Thread.sleep(500); imgRow33.setImageResource(R.drawable.smashball) }
            imgRow34.setOnClickListener() { Thread.sleep(500); imgRow34.setImageResource(R.drawable.smashball) }

            imgRow41.setOnClickListener() { Thread.sleep(500); imgRow41.setImageResource(R.drawable.smashball) }
            imgRow42.setOnClickListener() { Thread.sleep(500); imgRow42.setImageResource(R.drawable.smashball) }
            imgRow43.setOnClickListener() { Thread.sleep(500); imgRow43.setImageResource(R.drawable.smashball) }
            imgRow44.setOnClickListener() { Thread.sleep(500); imgRow44.setImageResource(R.drawable.smashball) }
            imgRow51.setOnClickListener() { Thread.sleep(500); imgRow51.setImageResource(R.drawable.smashball) }
            imgRow52.setOnClickListener() { Thread.sleep(500); imgRow52.setImageResource(R.drawable.smashball) }

            imgRow53.setOnClickListener() { Thread.sleep(500); imgRow53.setImageResource(R.drawable.smashball) }
            imgRow54.setOnClickListener() { Thread.sleep(500); imgRow54.setImageResource(R.drawable.smashball) }
            imgRow61.setOnClickListener() { Thread.sleep(500); imgRow61.setImageResource(R.drawable.smashball) }
            imgRow62.setOnClickListener() { Thread.sleep(500); imgRow62.setImageResource(R.drawable.smashball) }
            imgRow63.setOnClickListener() { Thread.sleep(500); imgRow63.setImageResource(R.drawable.smashball) }
            imgRow64.setOnClickListener() { Thread.sleep(500); imgRow64.setImageResource(R.drawable.smashball) }
        }

        /*Se generaron tableros predefinidos que serán cargados de manera aleatoria de acuerdo al número
    * aleatorio que se haya mandado como parámetro a la función*/
        fun generarTablero(numeroTablero: Int) {
            when(numeroTablero) {
                1 -> {
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
                2 -> {
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
                3 -> {
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
                4 -> {
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
    private fun partidaEnd(){
        val intent = Intent(this@Tablero, FinalOnline::class.java).apply {
            putExtra("personaje",personajeNumber)
        }
        startActivity(intent)
    }
    private fun preguntarPersonaje1(personaje : String){
                val temp = listaPersonaje[personajeNumber-1]
                Log.d("pp1",temp.name)
                if(temp.name == personaje) {
                    partidaEnd()
                }else{
                    Toast.makeText(this@Tablero,
                        "No es $personaje", Toast.LENGTH_SHORT).show()
                }
    }
    private fun preguntarPersonajeCombate1(combate: Int){
                val temp = listaPersonaje[personajeNumber-1]
                if(temp.combate == combate){
                    Toast.makeText(this@Tablero,
                        "Si combate con $tempstring", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this@Tablero,
                        "No combate con $tempstring", Toast.LENGTH_SHORT).show()
                }
    }

    private fun preguntarPersonajeFranq1(franq: Int){
                val temp = listaPersonaje[personajeNumber-1]
                if(temp.franquicia == franq){
                    Toast.makeText(this@Tablero,
                        "Si es de la franquicia $tempstring", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this@Tablero,
                        "No es de la franquicia $tempstring", Toast.LENGTH_SHORT).show()
                }
    }
    private fun preguntarPersonajeTamano1(tam: Int){
                val temp = listaPersonaje[personajeNumber-1]
                if(temp.tamano == tam){
                    Toast.makeText(this@Tablero,
                        "Si es de tamaño $tempstring", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this@Tablero,
                        "No es de tamaño $tempstring", Toast.LENGTH_SHORT).show()
                }
            }
    
}