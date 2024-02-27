package com.repasoAndroid.androidmaster.imccalculator

import android.content.Intent
import android.health.connect.datatypes.WeightRecord
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.RangeSlider
import com.repasoAndroid.androidmaster.R
import org.w3c.dom.Text
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale

class imcCalculatorActivity : AppCompatActivity() {
//Ponemos la declaracion de los componetnes aqui para poder acceder
//    a estas variables desde el resto de metodos y se inicializa en el onCreate
//    Lateview= no te inicies ahora sino cuando yo te diga

    private var isMaleSelected: Boolean = true
    private var isFemaleSelected: Boolean = false
    private var currentWeight:Int= 70
    private var currentHeight:Int = 150
    private var currentAge:Int= 25


    private lateinit var viewMale: CardView
    private lateinit var viewFemale: CardView
    private lateinit var tvHeight:TextView
    private lateinit var rsHeight:RangeSlider

    private lateinit var btnSubtractWeight: FloatingActionButton
    private lateinit var btnPlusWeight: FloatingActionButton
    private lateinit var tvWeight:TextView

    private lateinit var btnSubtractAge: FloatingActionButton
    private lateinit var btnPlusAge: FloatingActionButton
    private lateinit var tvAge:TextView

    private lateinit var btnCalculate: Button

    //Definimos una constante que se puede acceder desde otras patallas. Equivale a un static de java
    companion object{
        const val IMC_KEY="IMC_RESULT"
    }
    //Siempre es el priemr método que se ejecuta
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imc_calculator)

        initComponent()
        initListeners()
        initUI()
    }


    //        Iniciliza las componentes
    private fun initComponent() {
        viewMale = findViewById(R.id.viewMale)
        viewFemale = findViewById(R.id.viewFemale)
        tvHeight = findViewById(R.id.tvHeight)
        rsHeight = findViewById(R.id.rsHeight)
        btnSubtractWeight = findViewById(R.id.btnSubtractWeight)
        btnPlusWeight = findViewById(R.id.btnPlusWeight)
        tvWeight=findViewById(R.id.tvWeight)
        btnPlusAge=findViewById(R.id.btnPlusAge)
        btnSubtractAge=findViewById(R.id.btnSubtractAge)
        tvAge=findViewById(R.id.tvAge)
        btnCalculate=findViewById(R.id.btnCalculate)
    }

    //    Accede a las variables de la funcion initComponent
//    y añade un listener de click
    private fun initListeners() {
        viewMale.setOnClickListener {
            changeGender()
            setGenderColor()
        }
        viewFemale.setOnClickListener {
            changeGender()
            setGenderColor()
        }
//        Listener para cada vez que cambie el valor del slider
        rsHeight.addOnChangeListener { _, value, _ ->
//            defino el formato de 1 valor y 2 decimales para que no haya decimales

            val df = DecimalFormat("#.##")
            currentHeight=df.format(value).toInt()
            tvHeight.text= "$currentHeight cm"
        }

        btnPlusWeight.setOnClickListener{
            currentWeight+=1
            setWeight()
        }
        btnSubtractWeight.setOnClickListener{
            currentWeight-=1
            setWeight()
            setAge()
        }

        btnPlusAge.setOnClickListener {
            currentAge+=1
            setAge()
        }
        btnSubtractAge.setOnClickListener{
            currentAge-=1
            setAge()
        }
        btnCalculate.setOnClickListener {
            val result = calculateIMC()
//            Navegamos a la pantalla con el resultado del imc
            navigateToResult(result)
        }
    }

    private fun navigateToResult(result: Double) {
        val intent=Intent(this,ResultIMCActivity::class.java)
        intent.putExtra(IMC_KEY,result)
        startActivity(intent)
    }

    private fun calculateIMC(): Double {
        val heightInMeters = currentHeight.toDouble() / 100
        val imc = currentWeight / (heightInMeters * heightInMeters)
        // configuración regional que utilice un punto como separador decimal, como en Estados Unidos
        val df = DecimalFormat("#.##", DecimalFormatSymbols(Locale.US))
        val formattedImc = df.format(imc)
        return formattedImc.replace(',', '.').toDouble()
    }

    private fun setAge(){
        tvAge.text=currentAge.toString()
    }
    private fun setWeight(){
        tvWeight.text=currentWeight.toString()
    }

    private fun changeGender() {
        isMaleSelected = !isMaleSelected
        isFemaleSelected = !isFemaleSelected
    }

    private fun setGenderColor() {

        viewMale.setCardBackgroundColor(getBackgroundColor(isMaleSelected))
        viewFemale.setCardBackgroundColor(getBackgroundColor(isFemaleSelected))
    }

    private fun getBackgroundColor(isSelectedComponent: Boolean): Int {
        val colorReference = if (isSelectedComponent) {

            R.color.background_component_selected
        } else {
            R.color.background_component
        }
        //Para acceder a un color se usa esto
        return ContextCompat.getColor(this, colorReference)
    }

    //Inicia la interfaz
    private fun initUI() {
        setGenderColor()
//        Hay que llamarlo porque sino no se muestra un valor hasta pulsar en el + o -
        setWeight()
        setAge()
    }

}