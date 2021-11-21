package mx.uaa.mafl.projecttest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth

class UserLogin : AppCompatActivity() {

    //Definición de variables y recursos
    private lateinit var registrar : Button
    private lateinit var iniciar : Button
    private lateinit var email : EditText
    private lateinit var password : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        // Splash
        Thread.sleep(2000)

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
        iniciar = findViewById(R.id.botonIniciar)
        email = findViewById(R.id.textEmail)
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

    private fun showHome(email:String, uid:String){
        val homeIntent = Intent(this,  MenuPrincipal::class.java).apply {
            //Inicializamos la variable prefs
            val prefs = getSharedPreferences("WidgetPrefs", MODE_PRIVATE)
            //Inicializamos el editor
            val editor = prefs.edit()
            editor.putString("uid", uid)
            editor.putString("email", email)
            editor.commit()
        }
        startActivity(homeIntent)
    }
}