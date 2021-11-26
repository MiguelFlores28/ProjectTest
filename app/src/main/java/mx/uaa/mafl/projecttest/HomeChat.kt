package mx.uaa.mafl.projecttest

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.*
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class HomeChat: AppCompatActivity() {
    private var username = ""
    var roomsList : ArrayList<String> = arrayListOf()
    private var database = Firebase.database
    private var roomsRef = database.reference
    private var roomRef = database.reference
    private lateinit var logOut : Button
    private lateinit var listView : ListView
    private lateinit var eventListener : ValueEventListener
    private var roomName = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        database = FirebaseDatabase.getInstance()
        setContentView(R.layout.activity_homechat)
        val prefs = getSharedPreferences("ProyectoPrefs", MODE_PRIVATE)
        username = prefs.getString("username","").toString()
        logOut = findViewById(R.id.botonLogOut)
        listView = findViewById(R.id.listView1)
        listView.setOnItemClickListener{ _, _, position, _ ->
            roomName = roomsList[position]
            roomRef = database.getReference("chat/$roomName")
            addRoomEventListener()
            finish()
        }
        roomsList.add("123")
        addRoomsEventListener()
    }
    private fun addRoomEventListener() {
        roomRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val intent = Intent(this@HomeChat, Chat::class.java).apply {
                    putExtra("destino", roomName)
                }
                startActivity(intent)
                finish()
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@HomeChat, "Error!", Toast.LENGTH_SHORT).show()
            }
        })
    }
    private fun addRoomsEventListener() {
        roomsRef = database.getReference("chat")
        eventListener = roomsRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                roomsList.clear()
                val rooms = dataSnapshot.children
                for (snapshot in rooms) {
                    val temp = snapshot.key.toString()
                    if(temp != username)roomsList.add(temp)
                    val adapter = ArrayAdapter(this@HomeChat,
                        android.R.layout.simple_list_item_1,roomsList)
                    listView.adapter = adapter
                }
            }
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@HomeChat, "Error!", Toast.LENGTH_SHORT).show()
            }
        })
    }
    override fun onDestroy() {
        super.onDestroy()
        roomRef.removeEventListener(eventListener)
    }
    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, MenuPrincipal::class.java))
        finish()
    }
}
