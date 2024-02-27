package com.repasoAndroid.androidmaster.firstapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import com.repasoAndroid.androidmaster.R

class FirstAppActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //El diseño que se le asigna a esta pantalla
        setContentView(R.layout.activity_first_app)
        val btnStart = findViewById<AppCompatButton>(R.id.btnStart)
        val etName = findViewById<AppCompatEditText>(R.id.etName)



        //Cuando pulse el botón haz lo que hay dentro
        btnStart.setOnClickListener{
            val name = etName.text.toString()
            if (name.isNotEmpty()){
               //Creamos u  intent apra lanzar la siguente pantalla, es decir para transicionar
                val intent = Intent(this,ResultActivity::class.java)
                //Hago que el intent lleve una cadena consigo para verlo en la segunda pantalla
                intent.putExtra("EXTRA_NAME",name)
                startActivity(intent)
            }

        }
        //Al arrancar la pantalla
    }
}