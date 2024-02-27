package com.repasoAndroid.androidmaster.firstapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.repasoAndroid.androidmaster.R

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        val tvResult = findViewById<TextView>(R.id.tvResult)
        //Accedemos a la clave, ene ste caso EXTRA_NAME.
        val name:String = intent.extras?.getString("EXTRA_NAME").orEmpty()
        tvResult.text= "Hola $name"
    }
}