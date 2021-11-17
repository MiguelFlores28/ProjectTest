package mx.uaa.mafl.projecttest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kotlin.math.log

class UserLogin : AppCompatActivity() {

    private lateinit var txtUsuario: EditText
    private lateinit var txtPassword: EditText
    private lateinit var btnIniciar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_user_login)

        txtUsuario = findViewById(R.id.txtUSR)
        txtPassword = findViewById(R.id.txtPSWRD)
        btnIniciar = findViewById(R.id.btnIniciarSesion)

        var usuario: String = "0"
        var password: String = "0"
        var flag: Int = 0

            btnIniciar.setOnClickListener {
                usuario = txtUsuario.getText().toString()
                password = txtPassword.getText().toString()

                if(usuario=="mike" && password=="pass"){
                    startActivity(Intent(this, MenuPrincipal::class.java))
                }
                else{
                    val msj = Toast.makeText(
                        this,
                        "Usuario y/o contraseña incorrectos",
                        Toast.LENGTH_LONG).show()
                }
            }
    }
}