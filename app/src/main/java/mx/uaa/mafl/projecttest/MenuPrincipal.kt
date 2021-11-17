package mx.uaa.mafl.projecttest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.Toast

class MenuPrincipal : AppCompatActivity() {
    private var backPressedTime = 0L
    private lateinit var btnJVJ : Button
    private lateinit var btnJVC : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_menu_principal)

        btnJVJ = findViewById(R.id.btnPVP)
        btnJVC = findViewById(R.id.btnPVC)

        btnJVC.setOnClickListener() {
            startActivity(Intent(this, CharSelect::class.java))
        }
    }

    override fun onBackPressed() {
        if(backPressedTime + 2000 > System.currentTimeMillis()){
            super.onBackPressed()
        }
        else{
            Toast.makeText(this, "Presiona atrás otra vez para salir", Toast.LENGTH_LONG).show()
        }
        backPressedTime = System.currentTimeMillis()
    }
}