package mx.uaa.mafl.projecttest

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import java.util.*

class OnlineUserActivity : AppCompatActivity() {

    var user: FirebaseUser? = null
    var db: FirebaseDatabase? = null
    var usersListRef: DatabaseReference? = null
    var onlineStatus: DatabaseReference? = null
    var connectedRef: DatabaseReference? = null
    var userListValueEventListener: ValueEventListener? = null
    lateinit var userListView : ListView
    var userListItems: ArrayList<String>? = null
    var adapter: ArrayAdapter<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_online_user)
        userListView = findViewById(R.id.userListView)
        userListItems = ArrayList()
        db = FirebaseDatabase.getInstance()
        usersListRef = db!!.getReference("users")
        user = FirebaseAuth.getInstance().currentUser
        populateUserList()
    }

    private fun populateUserList() {

        userListValueEventListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                userListItems!!.clear()
                //first check datasnap shot exist
                //then add all users except current/self user
                if (dataSnapshot.exists()) {
                    for (ds in dataSnapshot.children) {
                        if (ds.exists() && ds.key != user!!.uid) {
                            val name = ds.child("username").getValue(String::class.java)!!
                            val onlineStatus = ds.child("onlineStatus").getValue(String::class.java)!!
                            userListItems!!.add("$name status : $onlineStatus")
                        }
                    }
                }
                adapter = ArrayAdapter(this@OnlineUserActivity,
                    android.R.layout.simple_list_item_1, android.R.id.text1, userListItems!!
                )
                userListView.adapter = adapter
                adapter!!.notifyDataSetChanged()
            }

            override fun onCancelled(databaseError: DatabaseError) {}
        }
        usersListRef!!.addValueEventListener(userListValueEventListener!!)
    }

    override fun onDestroy() {
        super.onDestroy()
        usersListRef!!.removeEventListener(userListValueEventListener!!)
        Toast.makeText(this@OnlineUserActivity, "SI ENTRO AL DESTROY",Toast.LENGTH_LONG).show()
    }
    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, MenuPrincipal::class.java))
        finish()
    }
}