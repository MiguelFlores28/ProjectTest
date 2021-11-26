package mx.uaa.mafl.projecttest

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

/*Código que nos muestra el menú principal, nos dirije a las actividades de:
* Guía de juego
* Juego contra un jugador
* Juego contra Computadora*/

class MenuPrincipal : AppCompatActivity() {

    //Definición de recursos y variables
    private var backPressedTime = 0L
    private lateinit var btnJVJ : Button
    private lateinit var btnJVC : Button
    private lateinit var btnGuia : Button
    private lateinit  var btnOnline : Button
    private lateinit  var btnMensaje : Button
    var db: FirebaseDatabase? = null
    var connectedRef: DatabaseReference? = null
    var onlineStatus: DatabaseReference? = null
    var username = ""
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        val prefs = getSharedPreferences("ProyectoPrefs", MODE_PRIVATE)
        //Inicializamos el editor
        username = prefs.getString("username","").toString()
        setContentView(R.layout.layout_menu_principal)
        //personajeApp = this as PersonajeApp
        //Definición de recursos y variables
        btnJVJ = findViewById(R.id.btnPVP)
        btnJVC = findViewById(R.id.btnPVC)
        btnGuia = findViewById(R.id.btnGuia)
        btnOnline = findViewById(R.id.botonUsers)
        btnMensaje = findViewById(R.id.botonMens)
        db = FirebaseDatabase.getInstance()
        //A la actividad del juego contra máquina
        btnJVC.setOnClickListener {
            startActivity(Intent(this, Tablero::class.java))
        }
        btnMensaje.setOnClickListener{
            startActivity(Intent(this, HomeChat::class.java))
        }
        //A la actividad de guía
       btnGuia.setOnClickListener {
           val intent = Intent(this@MenuPrincipal, GuiaJuego::class.java).apply {
               putExtra("band","guia")
           }
           startActivity(intent)
       }

        btnJVJ.setOnClickListener{
            val intent = Intent(this@MenuPrincipal, GuiaJuego::class.java).apply {
                putExtra("band","pvp")
            }
            startActivity(intent)
        }
        //Agregar funcionalidad al botón de jugador contra jugador
        btnOnline.setOnClickListener{
            startActivity(Intent(this, OnlineUserActivity::class.java))
        }
        addToUserList(FirebaseAuth.getInstance().currentUser)
    }

    /*Botón atrás, se ocupan dos clics para salir*/
    override fun onBackPressed() {
        if(backPressedTime + 2000 > System.currentTimeMillis()){ super.onBackPressed() }
        else{ Toast.makeText(this, "Presiona atrás otra vez para salir", Toast.LENGTH_LONG).show() }
        backPressedTime = System.currentTimeMillis()
    }

    private fun addToUserList(user: FirebaseUser?) {
        onlineStatus = db!!.getReference("users/" + user?.uid + "/onlineStatus")
        connectedRef = FirebaseDatabase.getInstance().getReference(".info/connected")
        connectedRef!!.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val connected = snapshot.getValue(Boolean::class.java)!!
                if (connected) {
                    onlineStatus!!.onDisconnect().setValue("offline")
                    onlineStatus!!.setValue("Online")
                } else {
                    onlineStatus!!.setValue("offline")
                }
            }

            override fun onCancelled(error: DatabaseError) {}
        })
    }


}
