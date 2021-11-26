package mx.uaa.mafl.projecttest

import android.content.Intent
import android.os.Bundle
import android.util.Log
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
import java.time.temporal.Temporal

class Chat : AppCompatActivity() {
    private var username = ""
    var roomsList : ArrayList<String> = arrayListOf()
    private var database = Firebase.database
    private var roomsRef = database.reference
    private var roomsRef2 = database.reference
    private lateinit var logOut : Button
    private lateinit var listView : ListView
    private var destino = ""
    private var cont = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        database = FirebaseDatabase.getInstance()
        setContentView(R.layout.activity_homechat)
        val prefs = getSharedPreferences("ProyectoPrefs", MODE_PRIVATE)
        username = prefs.getString("username","").toString()
        logOut = findViewById(R.id.botonLogOut)
        listView = findViewById(R.id.listView1)
        val extras = intent.extras
        if(extras != null) {
            destino = extras.getString("destino").toString()
        }
        roomsList.add("123")
        Log.d("destino = ",destino)
        addRoomsEventListener()
    }
    private fun addRoomsEventListener() {
        roomsRef = database.getReference("chat/$username")
        roomsRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                roomsList.clear()
                val rooms = dataSnapshot.children
                val temporal1 = rooms.toMutableList()
                addRoomsEventListener2(temporal1)
            }
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@Chat, "Error!", Toast.LENGTH_SHORT).show()
            }
        })
    }
    private fun addRoomsEventListener2(temporal1:  MutableList<DataSnapshot>){
        roomsRef2 = database.getReference("chat/$destino")
        roomsRef2.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                roomsList.clear()
                val rooms = dataSnapshot.children
                val temporal2 = rooms.toMutableList()
                if(temporal1.size >= temporal2.size){
                    for(i in 0..temporal1.size){
                        if(i <= temporal1.size-1)
                        if(temporal1[i].child("destino").value == destino){
                            roomsList.add("$username: "+temporal1[i].child("mensaje").value.toString())
                        }
                        if(i <= temporal2.size-1){
                            if(temporal2[i].child("destino").value == username){
                                roomsList.add("$destino: "+temporal2[i].child("mensaje").value.toString())
                            }
                        }
                    }
                }else{
                    for(i in 0..temporal2.size){
                        if(i <= temporal1.size-1)
                            if(temporal1[i].child("destino").value == destino){
                                roomsList.add("$username: "+temporal1[i].child("mensaje").value.toString())
                            }
                        if(i <= temporal2.size-1){
                            if(temporal2[i].child("destino").value == username){
                                roomsList.add("$destino: "+temporal2[i].child("mensaje").value.toString())
                            }
                        }
                    }
                }
                val adapter = ArrayAdapter(this@Chat,
                    android.R.layout.simple_list_item_1,roomsList)
                listView.adapter = adapter
            }
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@Chat, "Error!", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, HomeChat::class.java))
        finish()
    }
}
