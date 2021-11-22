package mx.uaa.mafl.projecttest

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class NombreUser : AppCompatActivity()  {
    private lateinit var botonContinuar : Button
    private lateinit var textUsername : EditText
    private var database = Firebase.database
    private var userRef = database.reference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_name)
        botonContinuar = findViewById(R.id.botonContinuar)
        textUsername = findViewById(R.id.textUsername)
        val prefs = getSharedPreferences("ProyectoPrefs", MODE_PRIVATE)
        val editor = prefs.edit()
        val uid = prefs.getString("uid","").toString()
        botonContinuar.setOnClickListener{
            if (textUsername.text.isNotEmpty()){
                editor.putString("username",textUsername.text.toString())
                editor.commit()
                userRef = database.getReference("users/$uid/username")
                userRef.setValue(textUsername.text.toString())
                val homeIntent = Intent(this, MenuPrincipal::class.java).apply {
                }
                startActivity(homeIntent)
            }
        }
    }


}