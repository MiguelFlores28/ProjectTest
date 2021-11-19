package mx.uaa.mafl.projecttest

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.AnimationDrawable
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources

/*Código que hace la selección aleatoria de personaje*/

class CharSelect : AppCompatActivity() {

    //Definición de recursos y variables
    private var backPressedTime = 0L
    var CharNmbr : Int = 0
    private lateinit var imgchar : ImageView
    private lateinit var txtChar : TextView
    private lateinit var btnSiguiente : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_char_select)

        /*Definición de recursos y variables*/
        imgchar = findViewById(R.id.imgChrctrSelect)
        txtChar = findViewById(R.id.chrctrSelectTxt)
        btnSiguiente = findViewById(R.id.btnSelCharStart)
        var randChar : Int = (1..24).random()
        CharNmbr = randChar
        var names = arrayOf(
            "Mario","Bowser","Peach","Samus","Dark Samus","Ridley",
            "Donkey Kong","Diddy Kong","King K. Rool",
            "Pikachu","Greninja","Jigglypuff",
            "Kirby","Rey Dedede","MetaKnight", "Lucina","Roy","Ike",
            "Link","Zelda","Ganondorf","Fox","Falco","Wolf")

        /*Se genera un número aleatorio el cual será el que nos proporcionará un personaje al azar*/
        when (CharNmbr){
            1 ->{ imgchar.setImageResource(R.drawable.mario); txtChar.setText(names[0])}
            2 ->{ imgchar.setImageResource(R.drawable.bowser); txtChar.setText(names[1])}
            3 ->{ imgchar.setImageResource(R.drawable.peach); txtChar.setText(names[2])}
            4 ->{ imgchar.setImageResource(R.drawable.samus); txtChar.setText(names[3])}
            5 ->{ imgchar.setImageResource(R.drawable.dark_samus); txtChar.setText(names[4])}
            6 ->{ imgchar.setImageResource(R.drawable.ridley); txtChar.setText(names[5])}

            7 ->{ imgchar.setImageResource(R.drawable.dk); txtChar.setText(names[6])}
            8 ->{ imgchar.setImageResource(R.drawable.didykong); txtChar.setText(names[7])}
            9 ->{ imgchar.setImageResource(R.drawable.krool); txtChar.setText(names[8])}
            10 ->{ imgchar.setImageResource(R.drawable.pikachu); txtChar.setText(names[9])}
            11 ->{ imgchar.setImageResource(R.drawable.greninja); txtChar.setText(names[10])}
            12 ->{ imgchar.setImageResource(R.drawable.jigglypuff); txtChar.setText(names[11])}

            13 ->{ imgchar.setImageResource(R.drawable.kirby); txtChar.setText(names[12])}
            14 ->{ imgchar.setImageResource(R.drawable.dedede); txtChar.setText(names[13])}
            15 ->{ imgchar.setImageResource(R.drawable.mknight); txtChar.setText(names[14])}
            16 ->{ imgchar.setImageResource(R.drawable.lucina); txtChar.setText(names[15])}
            17 ->{ imgchar.setImageResource(R.drawable.roy); txtChar.setText(names[16])}
            18 ->{ imgchar.setImageResource(R.drawable.ike); txtChar.setText(names[17])}

            19 ->{ imgchar.setImageResource(R.drawable.hyrulelink); txtChar.setText(names[18])}
            20 ->{ imgchar.setImageResource(R.drawable.zelda); txtChar.setText(names[19])}
            21 ->{ imgchar.setImageResource(R.drawable.ganondorf); txtChar.setText(names[20])}
            22 ->{ imgchar.setImageResource(R.drawable.foxmcloud); txtChar.setText(names[21])}
            23 ->{ imgchar.setImageResource(R.drawable.falco); txtChar.setText(names[22])}
            24 ->{ imgchar.setImageResource(R.drawable.wolf); txtChar.setText(names[23])}

        }
        btnSiguiente.setOnClickListener() {
            setContentView(R.layout.layout_tablero)
            startActivity(Intent(this, Tablero::class.java).putExtra("Personaje", CharNmbr.toString()))
        }

    }

    /*Botón atrás, se ocupan dos clics para salir*/
    override fun onBackPressed() {
        if(backPressedTime + 2000 > System.currentTimeMillis()){ super.onBackPressed() }
        else{ Toast.makeText(this, "Presiona atrás otra vez para salir", Toast.LENGTH_LONG).show() }
        backPressedTime = System.currentTimeMillis()
    }
}