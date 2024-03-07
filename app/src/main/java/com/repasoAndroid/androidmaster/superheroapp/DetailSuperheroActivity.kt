package com.repasoAndroid.androidmaster.superheroapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.View
import androidx.core.view.isVisible
import com.repasoAndroid.androidmaster.R
import com.repasoAndroid.androidmaster.databinding.ActivityDetailSuperheroBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.math.roundToInt

class DetailSuperheroActivity : AppCompatActivity() {

    //    Declaramos una cte para acceder al id del superheroe en el intent
    //    las cte se ponen en mayúscula
    companion object {
        const val EXTRA_ID = "extra_id"
    }

    private lateinit var binding: ActivityDetailSuperheroBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailSuperheroBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        Recuperamos el id del superheroe que hemos usado para cambiar de pantalla
        val id: String = intent.getStringExtra(EXTRA_ID).orEmpty()
        getSuperheroInformation(id)
    }

    private fun getSuperheroInformation(id: String) {
        //        Lo que hagamos en esta llaves se hace en un hilo secundario, en este caso vamos a realizar
        //        una petición a la API
        CoroutineScope(Dispatchers.IO).launch {
            val superheroDetail =
                getRetrofit().create(ApiService::class.java).getSuperheroDetail(id)

            if (superheroDetail.body() != null) {
                runOnUiThread { createUI(superheroDetail.body()!!) }
            }
        }
    }

    private fun createUI(superhero: SuperHeroDetailResponse) {
        Picasso.get().load(superhero.image.url).into(binding.ivSuperhero)
        binding.tvSuperheroName.text = superhero.name
        prepareStats(superhero.powerstats)
        binding.tvSuperheroRealName.text = superhero.biography.fullName
        binding.tvPublisher.text = superhero.biography.publisher
    }

    private fun prepareStats(powerstats: PowerStatsResponse) {

        updateHeight(binding.viewCombat, powerstats.combat)
        updateHeight(binding.viewDurability, powerstats.durability)
        updateHeight(binding.viewSpeed, powerstats.speed)
        updateHeight(binding.viewStrength, powerstats.strength)
        updateHeight(binding.viewPower, powerstats.power)
        updateHeight(binding.viewIntelligence, powerstats.intelligence)

    }

    private fun updateHeight(view: View, stat: String) {
        //        params son los parámetros de la vista/componente
//        En algunos casos falla y puede ser porque algún valor devuelto es null
        if (stat != "null") {
            val params = view.layoutParams
            params.height = pxToDp(stat.toFloat())
            view.layoutParams = params
        } else {
            // Handle the case where stat is "null", perhaps by setting a default value or hiding the view
            // For example, setting the view height to 0 or some default value
            val params = view.layoutParams
            params.height = 0 // or some default value
            view.layoutParams = params
        }
    }

    //    Para que las barras de las estadísticas tengan un tamaño acorde a su valor hay
//    que pasar de pixel a dp porque el pixel no es una unidad como tal
    private fun pxToDp(px: Float): Int {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, px, resources.displayMetrics)
            .roundToInt()
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://superheroapi.com/") //La dirección BASE de la API que se va a consumir
            .addConverterFactory(GsonConverterFactory.create()) //Para convertir JSON que recibimos de la API
            .build()
    }
}

