package mx.uaa.mafl.projecttest

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class TableroOnline : AppCompatActivity() {

    //Definición de variables y recursos
    private lateinit var botonpreg1 : Button
    private lateinit var botonpreg2 : Button
    private lateinit var botonpreg3 : Button
    private lateinit var botonpreg4 : Button
    private lateinit var botonpreg5 : Button
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
    private lateinit var button : Button
    private var database = Firebase.database
    private var messageRef = database.reference
    private var personajeRef1 = database.reference
    private var personajeRef2 = database.reference
    private lateinit var botonPrueba : Button
    private var roomName = ""
    private var uid = ""
    private var role = ""
    private var message = ""
    private var band = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_tablero)

        //Definición de variables y recursos, recuperación del personaje desde la actividad CharSelect
        var personajeNumber = 4
        var numTableroRand = (1..4).random()

        imgPersonajeActual = findViewById(R.id.imgChrctrGame)
        botonpreg1 = findViewById(R.id.botonpreg1)
        botonpreg2 = findViewById(R.id.botonpreg2)
        botonpreg3 = findViewById(R.id.botonpreg3)
        botonpreg4 = findViewById(R.id.botonpreg4)
        botonpreg5 = findViewById(R.id.botonpreg5)
        database = FirebaseDatabase.getInstance()
        button = findViewById(R.id.btnEnviarMsj)
        button.isEnabled = false
        val extras = intent.extras
        if(extras != null) {
            roomName = extras.getString("roomName").toString()
            uid = extras.get("uid").toString()
            if(roomName == uid){
                role = "host"
            }else {
                role = "guest"
            }
        }
        button.setOnClickListener{
            button.isEnabled = false
            //botonPrueba.isEnabled = false
            message = "$role:Poked!"
            messageRef.setValue(message)
        }
        messageRef = database.getReference("rooms/$roomName/message")
        message = "$role:Poked!"
        messageRef.setValue(message)
        personajeRef1 = database.getReference("rooms/$roomName/player1personaje")
        personajeRef2 = database.getReference("rooms/$roomName/player2personaje")
        addRoomEventListener()
        /*Para el recyclerView
        frases.add("es bueno")
        frases.add("es malo")
        frases.add("es rojo")
        vistaMensajes = findViewById(R.id.recViewMsj)
        adapter = RecViewAdapter()
        vistaMensajes.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        vistaMensajes.adapter = adapter
        adapter.setOnItemClickListener(object : RecViewAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {

                Toast.makeText(this@Tablero,"Le diste click a $position",Toast.LENGTH_SHORT).show()

            }

        })*/
        botonpreg1.setOnClickListener{
            Toast.makeText(this@TableroOnline,"Le diste click a boton1",Toast.LENGTH_SHORT).show()
        }
        botonpreg2.setOnClickListener{
            Toast.makeText(this@TableroOnline,"Le diste click a boton2",Toast.LENGTH_SHORT).show()
        }
        botonpreg3.setOnClickListener{
            Toast.makeText(this@TableroOnline,"Le diste click a boton3",Toast.LENGTH_SHORT).show()
        }
        botonpreg4.setOnClickListener{
            Toast.makeText(this@TableroOnline,"Le diste click a boton4",Toast.LENGTH_SHORT).show()
        }
        botonpreg5.setOnClickListener{
            Toast.makeText(this@TableroOnline,"Le diste click a boton5",Toast.LENGTH_SHORT).show()
        }
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

        /*Se genera un número aleatorio el cual será el que nos proporcionará un personaje al azar*/
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

        //Llamada a la función de generar tablero, la cual nos generará un tablero de forma aleatoria
        generarTablero(numTableroRand)

        //Código para que al ser presionadas, las casillas "bajen" y cambien por el reverso de la "carta"
        imgRow11.setOnClickListener(){Thread.sleep(500); imgRow11.setImageResource(R.drawable.smashball)}
        imgRow12.setOnClickListener(){Thread.sleep(500); imgRow12.setImageResource(R.drawable.smashball)}
        imgRow13.setOnClickListener(){Thread.sleep(500); imgRow13.setImageResource(R.drawable.smashball)}
        imgRow14.setOnClickListener(){Thread.sleep(500); imgRow14.setImageResource(R.drawable.smashball)}
        imgRow21.setOnClickListener(){Thread.sleep(500); imgRow21.setImageResource(R.drawable.smashball)}
        imgRow22.setOnClickListener(){Thread.sleep(500); imgRow22.setImageResource(R.drawable.smashball)}

        imgRow23.setOnClickListener(){Thread.sleep(500); imgRow23.setImageResource(R.drawable.smashball)}
        imgRow24.setOnClickListener(){Thread.sleep(500); imgRow24.setImageResource(R.drawable.smashball)}
        imgRow31.setOnClickListener(){Thread.sleep(500); imgRow31.setImageResource(R.drawable.smashball)}
        imgRow32.setOnClickListener(){Thread.sleep(500); imgRow32.setImageResource(R.drawable.smashball)}
        imgRow33.setOnClickListener(){Thread.sleep(500); imgRow33.setImageResource(R.drawable.smashball)}
        imgRow34.setOnClickListener(){Thread.sleep(500); imgRow34.setImageResource(R.drawable.smashball)}

        imgRow41.setOnClickListener(){Thread.sleep(500); imgRow41.setImageResource(R.drawable.smashball)}
        imgRow42.setOnClickListener(){Thread.sleep(500); imgRow42.setImageResource(R.drawable.smashball)}
        imgRow43.setOnClickListener(){Thread.sleep(500); imgRow43.setImageResource(R.drawable.smashball)}
        imgRow44.setOnClickListener(){Thread.sleep(500); imgRow44.setImageResource(R.drawable.smashball)}
        imgRow51.setOnClickListener(){Thread.sleep(500); imgRow51.setImageResource(R.drawable.smashball)}
        imgRow52.setOnClickListener(){Thread.sleep(500); imgRow52.setImageResource(R.drawable.smashball)}

        imgRow53.setOnClickListener(){Thread.sleep(500); imgRow53.setImageResource(R.drawable.smashball)}
        imgRow54.setOnClickListener(){Thread.sleep(500); imgRow54.setImageResource(R.drawable.smashball)}
        imgRow61.setOnClickListener(){Thread.sleep(500); imgRow61.setImageResource(R.drawable.smashball)}
        imgRow62.setOnClickListener(){Thread.sleep(500); imgRow62.setImageResource(R.drawable.smashball)}
        imgRow63.setOnClickListener(){Thread.sleep(500); imgRow63.setImageResource(R.drawable.smashball)}
        imgRow64.setOnClickListener(){Thread.sleep(500); imgRow64.setImageResource(R.drawable.smashball)}

    }

    /*Se generaron tableros predefinidos que serán cargados de manera aleatoria de acuerdo al número
    * aleatorio que se haya mandado como parámetro a la función*/
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
    private fun addRoomEventListener() {
        messageRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (role == "host") {
                    if(snapshot.value.toString().contains("guest:")) {
                        button.isEnabled = true
                        //botonPrueba.isEnabled = true
                        Toast.makeText(this@TableroOnline,
                            "" + snapshot.value.toString().replace("guest:",""), Toast.LENGTH_SHORT).show()
                        band = 0
                    }
                } else {
                    if(snapshot.value.toString().contains("host:")) {
                        button.isEnabled = true
                        //botonPrueba.isEnabled = true
                        Toast.makeText(this@TableroOnline,
                            "" + snapshot.value.toString().replace("host:",""), Toast.LENGTH_SHORT).show()
                        band = 1
                    }
                }
            }
            override fun onCancelled(error: DatabaseError) {
                messageRef.setValue(message)
            }

        })
    }
}