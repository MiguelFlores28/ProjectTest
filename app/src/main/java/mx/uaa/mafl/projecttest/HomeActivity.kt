package mx.uaa.mafl.projecttest

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database

import com.google.firebase.ktx.Firebase

class HomeActivity :AppCompatActivity() {
    private lateinit var logOut : Button
    private lateinit var listView : ListView
    private lateinit var crear : Button
    private var database = Firebase.database
    private var roomRef = database.reference
    private var roomsRef = database.reference
    private var conectRef = database.reference
    private var roomName = ""
    private var banduid = ""
    private var conectado = ""
    var roomsList : ArrayList<String> = arrayListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        // Setup
        //Recuperamos las variables de Preferences con su widgetId unico
        val prefs = this.getSharedPreferences("ProyectoPrefs", Context.MODE_PRIVATE)
        val uid = prefs.getString("uid", "")
        val username = prefs.getString("username","")
        banduid = uid.toString()
        setup(uid ?: "",username?: "")
    }

    private fun setup(uid: String, username: String){
        database = FirebaseDatabase.getInstance()

        logOut = findViewById(R.id.botonLogOut)
        listView = findViewById(R.id.listView)
        title = "Inicio"
        crear = findViewById(R.id.botonCrear)
        crear.setOnClickListener {
            crear.text = "CREATING ROOM"
            crear.isEnabled = false
            roomName = username
            roomRef = database.getReference("rooms/$roomName/player1")
            addRoomEventListener()
            roomRef.setValue(username)
        }
        listView.setOnItemClickListener{ _, _, position, _ ->
            roomName = roomsList[position]
            roomRef = database.getReference("rooms/$roomName/player2")
            addRoomEventListener()
            roomRef.setValue(username)
        }

        logOut.setOnClickListener{
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this@HomeActivity, UserLogin::class.java)
            startActivity(intent)
        }
        roomsList.add("123")
        addRoomsEventListener()
    }

    private fun addRoomEventListener() {
        roomRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                crear.text = "CREAR SALA"
                crear.isEnabled = true
                val intent = Intent(this@HomeActivity, TableroOnline::class.java).apply {
                    putExtra("roomName", roomName)
                    putExtra("uid",banduid)
                }
                startActivity(intent)
            }

            override fun onCancelled(error: DatabaseError) {
                crear.text = "CREAR SALA"
                crear.isEnabled = true
                Toast.makeText(this@HomeActivity, "Error!", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun addRoomsEventListener() {
        roomsRef = database.getReference("rooms")
        roomsRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                roomsList.clear()
                val rooms = dataSnapshot.children
                for (snapshot in rooms) {
                    val temp = snapshot.key.toString()
                    roomsList.add(temp)
                    val adapter = ArrayAdapter(this@HomeActivity,android.R.layout.simple_list_item_1,roomsList)
                    listView.adapter = adapter
                }
            }

            override fun onCancelled(error: DatabaseError) {
                crear.text = "CREAR SALA"
                crear.isEnabled = true
                Toast.makeText(this@HomeActivity, "Error!", Toast.LENGTH_SHORT).show()
            }
        })
    }

}







