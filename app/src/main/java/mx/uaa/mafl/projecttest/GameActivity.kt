package mx.uaa.mafl.projecttest

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class GameActivity: AppCompatActivity()  {
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
        setContentView(R.layout.activity_game)
        database = FirebaseDatabase.getInstance()
        button = findViewById(R.id.button)
        botonPrueba = findViewById(R.id.buttonPrueba)
        //button.isEnabled = false
        val extras = intent.extras
        if(extras != null) {
            roomName = extras.getString("roomName").toString()
            uid = extras.get("uid").toString()
            Log.d("roomName: ",roomName)
            Log.d("uid: ", uid)
            if(roomName == uid){
                role = "host"
            }else {
                role = "guest"
            }
        }
        button.setOnClickListener{
            button.isEnabled = false
            botonPrueba.isEnabled = false
            message = "$role:Poked!"
            messageRef.setValue(message)
        }
        botonPrueba.setOnClickListener{
            botonPrueba.isEnabled = false
            if(band == 1){
                preguntarPersonaje1()
            }else{
                preguntarPersonaje2()
            }
        }
        messageRef = database.getReference("rooms/$roomName/message")
        message = "$role:Poked!"
        messageRef.setValue(message)
        personajeRef1 = database.getReference("rooms/$roomName/player1personaje")
        personajeRef2 = database.getReference("rooms/$roomName/player2personaje")
        addRoomEventListener()

    }

    private fun addRoomEventListener() {
        messageRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (role == "host") {
                    if(snapshot.value.toString().contains("guest:")) {
                        button.isEnabled = true
                        botonPrueba.isEnabled = true
                        Toast.makeText(this@GameActivity,
                        "" + snapshot.value.toString().replace("guest:",""), Toast.LENGTH_SHORT).show()
                        band = 0
                    }
                } else {
                    if(snapshot.value.toString().contains("host:")) {
                        button.isEnabled = true
                        botonPrueba.isEnabled = true
                        Toast.makeText(this@GameActivity,
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
    private fun preguntarPersonaje1(){
        personajeRef1.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                Log.d("pp1: ",snapshot.value.toString())
                if(snapshot.value.toString().contains("Bowser")) {
                    Toast.makeText(this@GameActivity,
                        "Si es bowser", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this@GameActivity,
                        "No es bowser", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
        }
    private fun preguntarPersonaje2(){
        personajeRef2.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                Log.d("pp2: ",snapshot.value.toString())
                if(snapshot.value.toString().contains("Bowser")) {
                    Toast.makeText(this@GameActivity,
                        "Si es bowser", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this@GameActivity,
                        "No es bowser", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
    }
