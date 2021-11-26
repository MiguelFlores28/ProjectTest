package mx.uaa.mafl.projecttest

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class FinalOnline : AppCompatActivity() {
    private lateinit var botonContinuar : Button
    private lateinit var imgchar: ImageView
    private lateinit var txtChar : TextView
    private lateinit var txtPos : TextView
    private lateinit var btnSiguiente : Button
    private var database = Firebase.database
    var roomName = ""
    var uid = ""
    var visita = ""
    var pers = 0
    var band = 1
    var username = ""
    var rival = ""
    private var updateWinRef = database.reference
    private var updateLostRef = database.reference
    private var tempRef = database.reference
    private var tempRef2 = database.reference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_final)
        imgchar = findViewById(R.id.imgChrctrSelect)
        txtChar = findViewById(R.id.chrctrSelectTxt)
        txtPos = findViewById(R.id.textPos)
        btnSiguiente = findViewById(R.id.btnSelCharStart)
        val prefs = getSharedPreferences("ProyectoPrefs", MODE_PRIVATE)
        username = prefs.getString("username","").toString()
        val idrival = prefs.getString("idrival","").toString()
        val textGP = prefs.getString("texto","").toString()
        val extras = intent.extras
        Log.d("idrival",idrival)
        Log.d("textGP",textGP)
        if(extras != null) {
            visita = extras.getString("visita").toString()
            if(visita == "online"){
                roomName = extras.getString("roomName").toString()
                uid = extras.get("uid").toString()
                rival = extras.getString("rival").toString()
            }else{
                band = 0
                pers = extras.getInt("personaje")
                setupImagenTexto(pers)
                txtPos.text = "¡¡GANASTE!!"
            }
        }
        if (band == 1){
            updateWinRef = database.getReference("users/$uid/Wins")
            updateLostRef = database.getReference("users/$uid/Losses")
            Log.d("roomnamefinal",roomName)
            tempRef = database.getReference("temp/$username/idrival")
            tempRef2 = database.getReference("temp/$username/texto")
            colocarPersonaje()
            colocarTexto()
            Toast.makeText(this,"TEXTO "+txtPos.text.toString(),Toast.LENGTH_LONG).show()
        }
        btnSiguiente.setOnClickListener{
            if(band == 1) deleteRoom()
            startActivity(Intent(this, MenuPrincipal::class.java))
        }
    }
    private fun colocarPersonaje(){
        tempRef.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                setupImagenTexto(snapshot.value.toString().toInt())
            }
            override fun onCancelled(error: DatabaseError) {
            }
        })
    }
    private fun colocarTexto(){
        tempRef2.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                txtPos.text = snapshot.value.toString()
                if( snapshot.value.toString() == "¡¡GANASTE!!"){
                    sumarWin()
                }else{
                    sumarLost()
                }
            }
            override fun onCancelled(error: DatabaseError) {
            }
        })
    }
    private fun sumarWin(){
        updateWinRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                updateWinRef.setValue(snapshot.value.toString().toInt() + 1)
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
    private fun sumarLost(){
        updateLostRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                updateLostRef.setValue(snapshot.value.toString().toInt() + 1)
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
    private fun setupImagenTexto(personaje : Int){
        /*Definición de recursos y variables*/
        var names = arrayOf(
            "Mario","Bowser","Peach","Samus","Dark Samus","Ridley",
            "Donkey Kong","Diddy Kong","King K. Rool",
            "Pikachu","Greninja","Jigglypuff",
            "Kirby","Rey Dedede","MetaKnight", "Lucina","Roy","Ike",
            "Link","Zelda","Ganondorf","Fox","Falco","Wolf")

        /*Se genera un número aleatorio el cual será el que nos proporcionará un personaje al azar*/
        when (personaje){
            1 ->{ imgchar.setImageResource(R.drawable.mario); txtChar.setText(names[0])}
            2 ->{ imgchar.setImageResource(R.drawable.bowser); txtChar.setText(names[1])}
            3 ->{ imgchar.setImageResource(R.drawable.peach); txtChar.setText(names[2])}
            4 ->{ imgchar.setImageResource(R.drawable.samus); txtChar.setText(names[3])}
            5 ->{ imgchar.setImageResource(R.drawable.dark_samus); txtChar.setText(names[4])}
            6 ->{ imgchar.setImageResource(R.drawable.ridley); txtChar.setText(names[5])}

            7 ->{ imgchar.setImageResource(R.drawable.dk); txtChar.setText(names[6])}
            8 ->{ imgchar.setImageResource(R.drawable.didykong); txtChar.setText(names[7])}
            9 ->{ imgchar.setImageResource(R.drawable.krool); txtChar.setText(names[8])}
            10 ->{ imgchar.setImageResource(R.drawable.pikachu); txtChar.setText(names[9])}
            11 ->{ imgchar.setImageResource(R.drawable.greninja); txtChar.setText(names[10])}
            12 ->{ imgchar.setImageResource(R.drawable.jigglypuff); txtChar.setText(names[11])}

            13 ->{ imgchar.setImageResource(R.drawable.kirby); txtChar.setText(names[12])}
            14 ->{ imgchar.setImageResource(R.drawable.dedede); txtChar.setText(names[13])}
            15 ->{ imgchar.setImageResource(R.drawable.mknight); txtChar.setText(names[14])}
            16 ->{ imgchar.setImageResource(R.drawable.lucina); txtChar.setText(names[15])}
            17 ->{ imgchar.setImageResource(R.drawable.roy); txtChar.setText(names[16])}
            18 ->{ imgchar.setImageResource(R.drawable.ike); txtChar.setText(names[17])}

            19 ->{ imgchar.setImageResource(R.drawable.hyrulelink); txtChar.setText(names[18])}
            20 ->{ imgchar.setImageResource(R.drawable.zelda); txtChar.setText(names[19])}
            21 ->{ imgchar.setImageResource(R.drawable.ganondorf); txtChar.setText(names[20])}
            22 ->{ imgchar.setImageResource(R.drawable.foxmcloud); txtChar.setText(names[21])}
            23 ->{ imgchar.setImageResource(R.drawable.falco); txtChar.setText(names[22])}
            24 ->{ imgchar.setImageResource(R.drawable.wolf); txtChar.setText(names[23])}

        }
    }
    private fun deleteRoom(){
        if(roomName == username){
            val roomsRef = database.getReference("rooms/$roomName")
            roomsRef.removeValue()
        }
        val tempDeleteRef = database.getReference("temp/$username")
        tempDeleteRef.removeValue()
    }
}