package com.repasoAndroid.androidmaster

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.repasoAndroid.androidmaster.firstapp.FirstAppActivity
import com.repasoAndroid.androidmaster.imccalculator.imcCalculatorActivity
import com.repasoAndroid.androidmaster.superheroapp.SuperHeroListActivity
import com.repasoAndroid.androidmaster.todoapp.TodoActivity

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        val btnSaludApp = findViewById<Button>(R.id.btnSaludApp)
        val btnImcApp=findViewById<Button>(R.id.btnIMCApp)
        val btnTODO=findViewById<Button>(R.id.btnTODO)
        val btnSuperhero=findViewById<Button>(R.id.btnSuperhero)
        btnSaludApp.setOnClickListener{navigateToSaludApp()}
        btnImcApp.setOnClickListener{navigateToImcApp()}
        btnTODO.setOnClickListener{navigateTodoApp()}
        btnSuperhero.setOnClickListener { navigateToSugerHeroApp()}
    }


    private fun navigateToImcApp() {
        val intent = Intent(this,imcCalculatorActivity::class.java)
        startActivity(intent)
    }

    //Método para navegar a la siguiente pantalla
    private fun navigateToSaludApp(){
        val intent = Intent(this,FirstAppActivity::class.java)
        startActivity(intent)
    }

    private fun navigateTodoApp() {
        val intent=Intent(this,TodoActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToSugerHeroApp() {
        val intent=Intent(this,SuperHeroListActivity::class.java)
        startActivity(intent)
    }
}