package mx.uaa.mafl.projecttest

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class UserLogin : AppCompatActivity() {

    //Definición de variables y recursos
    private lateinit var registrar : Button
    private lateinit var iniciar : Button
    private lateinit var email : EditText
    private lateinit var password : EditText
    private var database = Firebase.database
    private var emailRef = database.reference
    private lateinit var homeIntent : Intent
    private var username = ""
    var user: FirebaseUser? = null
    var db: FirebaseDatabase? = null
    var usersListRef: DatabaseReference? = null
    var connectedRef: DatabaseReference? = null
    var onlineStatus: DatabaseReference? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        // Splash
        Thread.sleep(2000)
        database = FirebaseDatabase.getInstance()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_user_login)

        //Analytics Event
        val analytics = FirebaseAnalytics.getInstance(this)
        val bundle = Bundle()
        bundle.putString("message", "Integración de Firebase completa")
        analytics.logEvent("InitScreen",bundle)
        setup()
    }
    private fun setup(){
        registrar = findViewById(R.id.botonRegistra)
        iniciar = findViewById(R.id.botonContinuar)
        email = findViewById(R.id.textUsername)
        password = findViewById(R.id.textPassword)
        registrar.setOnClickListener{
            if (email.text.isNotEmpty() && password.text.isNotEmpty()){
                FirebaseAuth.getInstance()
                    .createUserWithEmailAndPassword(email.text.toString(),
                        password.text.toString()).addOnCompleteListener {

                        if(it.isSuccessful){
                            showHome(it.result?.user?.email ?: "",it.result?.user?.uid ?: "")
                        }else{
                            showAlert()
                        }
                    }
            }
        }
        iniciar.setOnClickListener{
            if (email.text.isNotEmpty() && password.text.isNotEmpty()){
                FirebaseAuth.getInstance()
                    .signInWithEmailAndPassword(email.text.toString(),
                        password.text.toString()).addOnCompleteListener {

                        if(it.isSuccessful){
                            showHome(it.result?.user?.email ?: "", it.result?.user?.uid ?: "")
                        }else{
                            showAlert()
                        }
                    }
            }
        }
    }
    private fun showAlert(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error autenticando al usuario")
        builder.setPositiveButton("Aceptar",null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun showHome(email:String, uid:String) {
        emailRef = database.getReference("users/$uid/username")
        //Inicializamos la variable prefs
        val prefs = getSharedPreferences("ProyectoPrefs", MODE_PRIVATE)
        //Inicializamos el editor
        val editor = prefs.edit()
        editor.putString("uid", uid)
        editor.putString("email", email)
        db = FirebaseDatabase.getInstance()
        emailRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.value.toString() == "null") {
                    homeIntent = Intent(this@UserLogin, NombreUser::class.java).apply {
                    }
                } else {
                    username = snapshot.value.toString()
                    homeIntent = Intent(this@UserLogin, MenuPrincipal::class.java).apply {
                        editor.putString("username", username)
                    }
                }
                editor.commit()
                startActivity(homeIntent)
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }

}